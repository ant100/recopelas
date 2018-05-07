/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.mysql.cj.util.StringUtils;
import com.upc.entity.Genre;
import com.upc.entity.Title;
import com.upc.model.Interest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            String query = "SELECT * FROM TITULOS LIMIT 15";
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
                el.setPrices(rs.getString("titulo_premios"));
                el.setRatings(ratingsDAO.getByTitle(rs.getString("titulo_id")));
                list.add(el);
            }
            
            return list;
            
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

            String query = "SELECT DISTINCT TI.TITULO_ID, TITULO_NOMBRE, TITULO_RATED, TITULO_FECHA, TITULO_POSTER, TITULO_SINOPSIS, TITULO_PREMIOS FROM TITULOS TI " +
                            "INNER JOIN TITULOS_GENEROS TG ON TG.TITULO_ID = TI.TITULO_ID " +
                            "INNER JOIN TITULOS_ACTORES TA ON TA.TITULO_ID = TI.TITULO_ID " +
                            "INNER JOIN TITULOS_DIRECTORES TD ON TD.TITULO_ID = TI.TITULO_ID " +
                            "WHERE TI.TITULO_NOMBRE IS NOT NULL";
            
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
            
            query = query.concat(" LIMIT 20");
            
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
                el.setPrices(rs.getString("titulo_premios"));
                el.setRatings(ratingsDAO.getByTitle(rs.getString("titulo_id")));
                list.add(el);
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
}
