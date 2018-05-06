<?php
// mostrar errores para debug
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
// conectar a la base de datos
$link = mysqli_connect("127.0.0.1", "root", "root", "peliculas");
if (!$link) {
    echo "Error: Unable to connect to MySQL." . PHP_EOL;
    echo "Debugging errno: " . mysqli_connect_errno() . PHP_EOL;
    echo "Debugging error: " . mysqli_connect_error() . PHP_EOL;
    exit;
}
// iterar los archivos dentro del directorio
$dir = new DirectoryIterator('peliculator/csv');
foreach ($dir as $fileinfo) {
    if (!$fileinfo->isDot()){
        $file = $fileinfo->getPathname();
		if (($handle = fopen("http://".$_SERVER['HTTP_HOST']."/".$file, "r")) !== FALSE){
    		// leer el contenido del archivo csv
    		while (($data = fgetcsv($handle, 0, ",")) !== FALSE){
    			foreach($data as $d){
    				// extraer el id de la URL
					preg_match('/title\/(.*?)\/\?ref/', $d, $match);
					if(!empty($match[1])){
						// evitar ids duplicados
						$query = mysqli_query($link, "SELECT imdb_id FROM imdb WHERE imdb_imdbID ='".$match[1]."'");
						// verificar si hubo un error en el query
					    if (!$query)   { die('Error: ' . mysqli_error($link)); }

						if(mysqli_num_rows($query) == 0){
							// guardar id en la BD
						    mysqli_query($link,"INSERT INTO imdb(imdb_imdbID)
										VALUES ('".$match[1]."')") 
									or die(mysqli_error($link));
						}	
					}
    			}
        	}
        	fclose($handle);
        }
    }
}
// cerrar la conexion a la BD
mysqli_close($link);
