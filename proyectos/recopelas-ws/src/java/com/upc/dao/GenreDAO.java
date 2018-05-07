/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.entity.Actor;
import com.upc.entity.Genre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author leo
 */ 
@Singleton
public class GenreDAO {
     
    public List<Genre> getAll(){
        
        List<Genre> list = new ArrayList<Genre>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM GENEROS";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Genre el = new Genre();
                el.setId(Integer.parseInt(rs.getString("genero_id"))); 
                el.setName(rs.getString("genero_nombre"));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
    public List<Genre> getByTitle(String title){
        
        List<Genre> list = new ArrayList<Genre>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT GE.GENERO_ID, GE.GENERO_NOMBRE FROM GENEROS GE "
                    + "INNER JOIN TITULOS_GENEROS TG ON GE.GENERO_ID = TG.GENERO_ID "
                    + "WHERE TG.TITULO_ID = " + title;
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Genre el = new Genre();
                el.setId(Integer.parseInt(rs.getString("genero_id"))); 
                el.setName(rs.getString("genero_nombre"));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
}
