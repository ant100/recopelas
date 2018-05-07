/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.entity.Rating;
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
public class RatingDAO {
     
    public List<Rating> getByTitle(String titulo){
        
        List<Rating> list = new ArrayList<Rating>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT RS_NOMBRE, VALOR FROM RATINGS_SOURCE RS "
                    + "INNER JOIN TITULOS_RATINGS_SOURCE TI "
                    + "WHERE TI.RS_ID = 1 AND TI.TITULO_ID = " + titulo;
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Rating el = new Rating();
                el.setSource(rs.getString("rs_nombre")); 
                el.setValue(rs.getString("valor"));
                list.add(el);
            }
            
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
}
