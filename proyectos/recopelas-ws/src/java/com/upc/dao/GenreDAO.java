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
public class GenreDAO {
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/recopelas";
  
    public List<Genre> getAll(){
        
        List<Genre> list = new ArrayList<Genre>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM GENEROS";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Genre el = new Genre();
                el.setId(Integer.parseInt(rs.getString("genero_id"))); 
                el.setName(rs.getString("genero_nombre"));
                list.add(el);
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
}
