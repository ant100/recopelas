/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.entity.Title;
import com.upc.model.Interest;
import java.sql.Connection;
import java.sql.DriverManager;
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
            String query = "SELECT * FROM TITULOS LIMIT 10";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Title el = new Title();
                el.setId(Integer.parseInt(rs.getString("titulo_id"))); 
                el.setName(rs.getString("titulo_nombre"));
                el.setRated(rs.getString("titulo_rated"));
                el.setRealease(rs.getString("titulo_fecha"));
                el.setPosterPath(rs.getString("titulo_poster"));
                el.setSynopsis(rs.getString("titulo_sinopsis"));
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
            String query = "SELECT * FROM TITULOS LIMIT 10";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Title el = new Title();
                el.setId(Integer.parseInt(rs.getString("titulo_id"))); 
                el.setName(rs.getString("titulo_nombre"));
                el.setRated(rs.getString("titulo_rated"));
                el.setRealease(rs.getString("titulo_fecha"));
                el.setPosterPath(rs.getString("titulo_poster"));
                el.setSynopsis(rs.getString("titulo_sinopsis"));
                list.add(el);
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
}
