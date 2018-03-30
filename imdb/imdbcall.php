<?php
// mostrar errores para debug
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting( E_ALL | E_STRICT );
set_time_limit(0);

// conectar a la base de datos
$mysqli = new mysqli("localhost", "root", "root", "peliculas");
if ($mysqli->connect_errno) {
    printf("Connect failed: %s\n", $mysqli->connect_error);
    exit();
}

// llamamos 1000 pues es el limite del API de imdb

// The SQL query below says "return only 10 records, start on record 16 (OFFSET 15)":
// $sql = "SELECT * FROM Orders LIMIT 10 OFFSET 15";
$query = "SELECT imdb_imdbID FROM imdb order by imdb_id ASC limit 100";
$result = $mysqli->query($query);
$imdbids = array();

while ($row = $result->fetch_assoc()) 
{
	$imdbids[] = $row['imdb_imdbID'];
}

foreach($imdbids as $imdbid)
{
	// verificar que no exista en la BD
	$q = "SELECT titulo_id FROM titulos WHERE imdb_imdbID = '".$imdbid."'";
	// verificar si hubo un error en el query
	$res = $mysqli->query($q);
	if( $res == TRUE){
		if($res->num_rows < 1){
			// llamada al api
			$curl = curl_init();

			curl_setopt_array($curl, array(
			  CURLOPT_URL => "http://www.omdbapi.com/?i=".$imdbid."&apikey=94c48922&",
			  CURLOPT_RETURNTRANSFER => true,
			  CURLOPT_TIMEOUT => 30,
			  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
			  CURLOPT_CUSTOMREQUEST => "GET",
			  CURLOPT_HTTPHEADER => array(
			    "cache-control: no-cache"
			  ),
			));

			$response = curl_exec($curl);
			$err = curl_error($curl);

			$response = json_decode($response, true); //because of true, it's in an array

			// verificar que la pelicula no esté vacía
			if(!empty($response['Title']) && !empty($response['Production']))
			{
				// escapar variables
				if(!empty($response['Production'])){
					$production = $mysqli->real_escape_string($response['Production']);	
				}
				if(!empty($response['Title'])){
					$title = $mysqli->real_escape_string($response['Title']);	
				}
				if(!empty($response['Rated'])){
					$rated = $mysqli->real_escape_string($response['Rated']);	
				}	
				if(!empty($response['Released'])){
					$released = $mysqli->real_escape_string($response['Released']);	
				}	
				if(!empty($response['Runtime'])){
					$runtime = $mysqli->real_escape_string($response['Runtime']);	
				}
				if(!empty($response['Poster'])){
					$poster = $mysqli->real_escape_string($response['Poster']);	
				}
				if(!empty($response['Plot'])){
					$plot = $mysqli->real_escape_string($response['Plot']);	
				}
				if(!empty($response['Type'])){
					$type = $mysqli->real_escape_string($response['Type']);
				}
				if(!empty($response['Ratings'][0])){
					$imdb_rating = $mysqli->real_escape_string($response['Ratings'][0]['Value']);	
				}
				if(!empty($response['Ratings'][1])){
					$rotten_rating = $mysqli->real_escape_string($response['Ratings'][1]['Value']);
				}
			
				// tabla productora
				$query = "SELECT productora_id FROM productoras WHERE productora_nombre = '".$production."'";
				// verificar si hubo un error en el query
				$result = $mysqli->query($query);
				if( $result == TRUE){
					// verificar si la productora ya existe
					if($result->num_rows > 0){
						$row2 = $result->fetch_assoc();
						$productora_id = $row2['productora_id'];
					}else{
						// hacer el insert
						$sql1 = "INSERT INTO productoras (productora_nombre) VALUES ('".$production."')";
						if($mysqli->query($sql1) === TRUE) {
							$productora_id = $mysqli->insert_id;
						} else {
							echo "Error: " . $sql1 . "<br>" . $mysqli->error;
						}
					}
				}else{
					echo "Error: " . $query . "<br>" . $mysqli->error;
					echo $mysqli->error;
				}

				// tabla titulos
				$sql3 = "INSERT INTO titulos (titulo_nombre, titulo_rated, titulo_fecha, titulo_duracion, titulo_poster, titulo_sinopsis, titulo_tipo, titulo_premios, productora_id, imdb_imdbID) VALUES ('".$title."', '".$rated."', '".$released."', '".$runtime."', '".$poster."', '".$plot."', '".$type."', '".addslashes($response['Awards'])."',".$productora_id.",'".$imdbid."')";

				if ($mysqli->query($sql3) == TRUE) {
				    $titulo_id = $mysqli->insert_id;
				} else {
				    echo "Error: " . $sql3 . "<br>" . $mysqli->error;
				}

				// tabla directores
				$directores_array = explode(",", $response["Director"]);
				$directores_ids = array();
				foreach($directores_array as $director)
				{
					$director_escape = $mysqli->real_escape_string($director);
					$query2 = "SELECT director_id FROM directores WHERE director_nombre = '".trim($director_escape)."'";
					// verificar si hubo un error en el query
					$result1 = $mysqli->query($query2);
					if( $result1 == TRUE){
						// verificar si el actor ya existe
						if($result1->num_rows > 0){
							$row4 = $result1->fetch_assoc();
							$directores_ids[] = $row4['director_id'];
						}else{
							// hacer el insert
							$sql4 = "INSERT INTO directores (director_nombre) VALUES ('".trim($director_escape)."')";
							if($mysqli->query($sql4) == TRUE) {
								$directores_ids[] = $mysqli->insert_id;
							} else {
								echo "Error: " . $sql4 . "<br>" . $mysqli->error;
							}
						}
					}else{
						echo "Error: " . $query2 . "<br>" . $mysqli->error;
					}	
				}

				// tabla actores
				$actores_array = explode(",", $response['Actors']);
				$actores_ids = array();
				foreach($actores_array as $actor)
				{
					$actor_escape = $mysqli->real_escape_string($actor);
					$query3 = "SELECT actor_id FROM actores WHERE actor_nombre = '".trim($actor_escape)."'";
					// verificar si hubo un error en el query
					$result2 = $mysqli->query($query3);
					if( $result2 == TRUE){
						// verificar si el actor ya existe
						if($result2->num_rows > 0){
							$row4 = $result2->fetch_assoc();
							$actores_ids[] = $row4['actor_id'];
						}else{
							// hacer el insert
							$sql4 = "INSERT INTO actores (actor_nombre) VALUES ('".trim($actor_escape)."')";
							if($mysqli->query($sql4) == TRUE) {
								$actores_ids[] = $mysqli->insert_id;
							} else {
								echo "Error: " . $sql4 . "<br>" . $mysqli->error;
							}
						}
					}else{
						echo "Error: " . $query3 . "<br>" . $mysqli->error;
					}	
				}

				// tabla generos
				$generos_array = explode(",", $response['Genre']);
				$generos_ids = array();
				foreach($generos_array as $genero)
				{
					$genero_escape = $mysqli->real_escape_string($genero);

					$query4 = "SELECT genero_id FROM generos WHERE genero_nombre = '".trim($genero_escape)."'";
					// verificar si hubo un error en el query
					$result3 = $mysqli->query($query4);
					if( $result3 == TRUE){
						// verificar si el genero ya existe
						if($result3->num_rows > 0){
							$row5 = $result3->fetch_assoc();
							$generos_ids[] = $row5['genero_id'];
						}else{
							// hacer el insert
							$sql5 = "INSERT INTO generos (genero_nombre) VALUES ('".trim($genero_escape)."')";
							if($mysqli->query($sql5) == TRUE) {
								$generos_ids[] = $mysqli->insert_id;
							} else {
								echo "Error: " . $sql5 . "<br>" . $mysqli->error;
							}
						}
					}else{
						echo "Error: " . $query3 . "<br>" . $mysqli->error;
					}	
				}

				// tabla idiomas
				$idiomas_array = explode(",", $response['Language']);
				$idiomas_ids = array();
				foreach($idiomas_array as $idioma)
				{
					$idioma_escape = $mysqli->real_escape_string($idioma);

					$query5 = "SELECT idioma_id FROM idiomas WHERE idioma_nombre = '".trim($idioma_escape)."'";
					// verificar si hubo un error en el query
					$result4 = $mysqli->query($query5);
					if( $result4 == TRUE){
						// verificar si el genero ya existe
						if($result4->num_rows > 0){
							$row6 = $result4->fetch_assoc();
							$idiomas_ids[] = $row6['idioma_id'];
						}else{
							// hacer el insert
							$sql6 = "INSERT INTO idiomas (idioma_nombre) VALUES ('".trim($idioma_escape)."')";
							if($mysqli->query($sql6) == TRUE) {
								$idiomas_ids[] = $mysqli->insert_id;
							} else {
								echo "Error: " . $sql6 . "<br>" . $mysqli->error;
							}
						}
					}else{
						echo "Error: " . $query5 . "<br>" . $mysqli->error;
					}	
				}

				// tabla paises
				$paises_array = explode(",", $response['Country']);
				$paises_ids = array();
				foreach($paises_array as $pais)
				{
					$pais_escape = $mysqli->real_escape_string($pais);

					$query4 = "SELECT pais_id FROM paises WHERE pais_nombre = '".trim($pais_escape)."'";
					// verificar si hubo un error en el query
					$result3 = $mysqli->query($query4);
					if( $result3 == TRUE){
						// verificar si el genero ya existe
						if($result3->num_rows > 0){
							$row5 = $result3->fetch_assoc();
							$paises_ids[] = $row5['pais_id'];
						}else{
							// hacer el insert
							$sql5 = "INSERT INTO paises (pais_nombre) VALUES ('".trim($pais_escape)."')";
							if($mysqli->query($sql5) == TRUE) {
								$paises_ids[] = $mysqli->insert_id;
							} else {
								echo "Error: " . $sql5 . "<br>" . $mysqli->error;
							}
						}
					}else{
						echo "Error: " . $query3 . "<br>" . $mysqli->error;
					}	
				}

				// tabla titulos_directores
				foreach($directores_ids as $director_id)
				{
					$sql9 = "INSERT INTO titulos_directores (titulo_id, director_id) VALUES (".$titulo_id.", ".$director_id.")";
					if($mysqli->query($sql9) != TRUE) {
						echo "Error: " . $sql9 . "<br>" . $mysqli->error;
					} 
				}

				// tabla titulos_actores
				foreach($actores_ids as $actor_id)
				{
					$sql6 = "INSERT INTO titulos_actores (titulo_id, actor_id) VALUES (".$titulo_id.", ".$actor_id.")";
					if($mysqli->query($sql6) != TRUE) {
						echo "Error: " . $sql6 . "<br>" . $mysqli->error;
					} 
				}

				// tabla titulos_generos
				foreach($generos_ids as $genero_id)
				{
					$sql7 = "INSERT INTO titulos_generos (titulo_id, genero_id) VALUES (".$titulo_id.", ".$genero_id.")";
					if($mysqli->query($sql7) != TRUE) {
						echo "Error: " . $sql7 . "<br>" . $mysqli->error;
					} 
				}

				// tabla titulos_idiomas
				foreach($idiomas_ids as $idioma_id)
				{
					$sql7 = "INSERT INTO titulos_idiomas (titulo_id, idioma_id) VALUES (".$titulo_id.", ".$idioma_id.")";
					if($mysqli->query($sql7) != TRUE) {
						echo "Error: " . $sql7 . "<br>" . $mysqli->error;
					} 
				}

				// tabla titulos_generos
				foreach($paises_ids as $pais_id)
				{
					$sql7 = "INSERT INTO titulos_paises (titulo_id, pais_id) VALUES (".$titulo_id.", ".$pais_id.")";
					if($mysqli->query($sql7) != TRUE) {
						echo "Error: " . $sql7 . "<br>" . $mysqli->error;
					} 
				}

				// tabla titulos_ratings_source

					// imdb
					$sql8 = "INSERT INTO titulos_ratings_source (titulo_id, rs_id, valor) VALUES (".$titulo_id.", 1,'".$imdb_rating."')";
					if($mysqli->query($sql8) != TRUE) {
						echo "Error: " . $sql8 . "<br>" . $mysqli->error;
					} 

					// rotten tomatoes
					$sql9 = "INSERT INTO titulos_ratings_source (titulo_id, rs_id, valor) VALUES (".$titulo_id.", 2,'".$rotten_rating."')";
					if($mysqli->query($sql9) != TRUE) {
						echo "Error: " . $sql9 . "<br>" . $mysqli->error;
					}
			}

			curl_close($curl);	
		}

	}else{
		echo "Error: " . $q . "<br>" . $mysqli->error;
		echo $mysqli->error;
	}
		
}

$result->free();
$mysqli->close();



