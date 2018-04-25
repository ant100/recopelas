/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;
import com.upc.entity.Actor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;
/**
 *
 * @author leo
 */ 
public class ActorDAO {
     
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/recopelas";
  
    public List<Actor> getAll(){
        
        List<Actor> list = new ArrayList<Actor>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM ACTORES LIMIT 100";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Actor el = new Actor();
                el.setId(Integer.parseInt(rs.getString("actor_id"))); 
                el.setNames(rs.getString("actor_nombre"));
                list.add(el);
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
}
