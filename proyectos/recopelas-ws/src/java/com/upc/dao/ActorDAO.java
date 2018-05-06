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
@Singleton
public class ActorDAO {
      
    public List<Actor> getAll(){
        
        List<Actor> list = new ArrayList<Actor>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
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
