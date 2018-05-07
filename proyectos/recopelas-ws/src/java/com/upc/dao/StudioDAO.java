/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.entity.Studio;
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
public class StudioDAO {
       
    public List<Studio> getAll(){
        
        List<Studio> list = new ArrayList<Studio>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM PRODUCTORAS";
            ResultSet rs = stmt.executeQuery(query);
            RatingDAO ratingsDAO = new RatingDAO();
            
            while (rs.next()) {
                Studio el = new Studio();
                el.setId(Integer.parseInt(rs.getString("productora_id"))); 
                el.setName(rs.getString("productora_nombre"));
                list.add(el);
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
}
