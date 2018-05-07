/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.entity.Title;
import com.upc.entity.Yala;
import com.upc.model.Interest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leo
 */
public class TitleDAO {
     
    public List<Title> getAll(){
        
        List<Title> list = new ArrayList<Title>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM TITULOS ORDER BY RAND() LIMIT 15";
            ResultSet rs = stmt.executeQuery(query);
            RatingDAO ratingsDAO = new RatingDAO();
            
            while (rs.next()) {
                Title el = new Title();
                el.setId(Integer.parseInt(rs.getString("titulo_id"))); 
                el.setName(rs.getString("titulo_nombre"));
                el.setRated(rs.getString("titulo_rated"));
                el.setRealease(rs.getString("titulo_fecha"));
                el.setPosterPath(rs.getString("titulo_poster"));
                el.setSynopsis(rs.getString("titulo_sinopsis"));
                el.setPrizes(rs.getString("titulo_premios"));
                el.setRatings(ratingsDAO.getByTitle(rs.getString("titulo_id")));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
    public Title get(String Id){
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM TITULOS TI"
                    + " INNER JOIN PRODUCTORAS PR ON TI.PRODUCTORA_ID = PR.PRODUCTORA_ID"
                    + " WHERE TI.TITULO_ID = " + Id;
            ResultSet rs = stmt.executeQuery(query);
            
            RatingDAO ratingsDAO = new RatingDAO();            
            ActorDAO actorDAO = new ActorDAO();
            GenreDAO genreDAO = new GenreDAO();
            DirectorDAO directorDAO = new DirectorDAO();
            LanguageDAO languageDAO = new LanguageDAO();
            
            Title el = new Title();

            while (rs.next()) {
                el.setId(Integer.parseInt(rs.getString("titulo_id"))); 
                el.setName(rs.getString("titulo_nombre"));
                el.setRated(rs.getString("titulo_rated"));
                el.setDuration(rs.getString("titulo_duracion"));
                el.setRealease(rs.getString("titulo_fecha"));
                el.setPosterPath(rs.getString("titulo_poster"));
                el.setSynopsis(rs.getString("titulo_sinopsis"));
                el.setPrizes(rs.getString("titulo_premios"));
                el.setStudio(rs.getString("productora_nombre"));
                el.setRatings(ratingsDAO.getByTitle(rs.getString("titulo_id")));
                el.setActors(actorDAO.getByTitle(rs.getString("titulo_id")));
                el.setGenres(genreDAO.getByTitle(rs.getString("titulo_id")));
                el.setDirectors(directorDAO.getByTitle(rs.getString("titulo_id")));
                el.setLanguages(languageDAO.getByTitle(rs.getString("titulo_id")));
            }
            conn.close();
            return el;
            
        }catch (SQLException e){
            return null;
        }
    }
    
    public List<Title> gerRecommended(Interest interests){
        
        List<Title> list = new ArrayList<Title>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();

            String viewed = String.join(",", getViewed(interests.getUser()));
            
            String query = "SELECT DISTINCT TI.TITULO_ID, TITULO_NOMBRE, TITULO_RATED, TITULO_FECHA, TITULO_POSTER, TITULO_SINOPSIS, TITULO_PREMIOS FROM TITULOS TI " +
                            "INNER JOIN TITULOS_GENEROS TG ON TG.TITULO_ID = TI.TITULO_ID " +
                            "INNER JOIN TITULOS_ACTORES TA ON TA.TITULO_ID = TI.TITULO_ID " +
                            "INNER JOIN TITULOS_DIRECTORES TD ON TD.TITULO_ID = TI.TITULO_ID " +
                            "WHERE TI.TITULO_ID NOT IN (" + viewed + ")";
            
            if (interests.getGenres() != null){
                query = query.concat(" AND TG.GENERO_ID IN (" + interests.getGenresId() + ")");
            }
            
            if (interests.getActors() != null){
                query = query.concat(" AND TA.ACTOR_ID IN (" + interests.getActorsId() + ")");
            }
            
            if (interests.getYears() != null){
                query = query.concat(" AND SUBSTRING_INDEX(TI.TITULO_FECHA, ' ', -1) IN ("  + interests.getYearsId() + ")");
            }
            
            if (interests.getDirectors() != null){
                query = query.concat(" AND TD.DIRECTOR_ID IN (" + interests.getDirectorsId() + ")");
            }

            if (interests.getStudios() != null){
                query = query.concat(" AND TI.PRODUCTORA_ID IN (" + interests.getStudiosId()+ ")");
            }
            
            query = query.concat(" ORDER BY RAND() LIMIT 20");
            
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            RatingDAO ratingsDAO = new RatingDAO();
            
            while (rs.next()) {
                Title el = new Title();
                el.setId(Integer.parseInt(rs.getString("titulo_id"))); 
                el.setName(rs.getString("titulo_nombre"));
                el.setRated(rs.getString("titulo_rated"));
                el.setRealease(rs.getString("titulo_fecha"));
                el.setPosterPath(rs.getString("titulo_poster"));
                el.setSynopsis(rs.getString("titulo_sinopsis"));                
                el.setPrizes(rs.getString("titulo_premios"));
                el.setRatings(ratingsDAO.getByTitle(rs.getString("titulo_id")));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }

    public List<String> getViewed(String user){
        
        List<String> list = new ArrayList<String>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            String query = "SELECT TITULO_ID FROM YALAS YA " +
                            "WHERE YALA_ESTADO = ? AND USUARIO_ID= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, "1");
            ps.setString(2, user);
            ResultSet rs = ps.executeQuery(); 
            
            while (rs.next()) {
                list.add(rs.getString("titulo_id"));
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
    public void saveViewed(Yala yala) throws SQLException {
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            String query = "SELECT * FROM YALAS WHERE TITULO_ID = ? AND USUARIO_ID= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, yala.getTitle());
            ps.setInt(2, yala.getUser());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                System.out.println("Actualizado");
                String update = "UPDATE YALAS SET YALA_ESTADO = ?, YALA_FECH_REGISTRO = NOW() WHERE TITULO_ID = ? AND USUARIO_ID = ?";
                PreparedStatement psupdate = conn.prepareStatement(update);
                psupdate.setString(1, yala.getValue());
                psupdate.setInt(2, yala.getTitle());
                psupdate.setInt(3, yala.getUser());
                psupdate.executeUpdate();
                
            }else
            {
                System.out.println("Insertado");
                String insert = "INSERT INTO YALAS VALUES (?, ?, ?, NOW())";
                PreparedStatement psinsert = conn.prepareStatement(insert);
                psinsert.setInt(1, yala.getTitle());
                psinsert.setInt(2, yala.getUser());
                psinsert.setString(3, yala.getValue());
                psinsert.execute();
            }
  
            
        }catch (SQLException e){
            throw e;
        }
        
        
    }
    
    
    
}
