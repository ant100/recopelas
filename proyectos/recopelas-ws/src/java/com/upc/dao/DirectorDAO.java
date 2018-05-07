/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;
 
import com.upc.entity.Director;
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
public class DirectorDAO {
  
    public List<Director> getAll(){
        
        List<Director> list = new ArrayList<Director>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT * FROM DIRECTORES";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Director el = new Director();
                el.setId(Integer.parseInt(rs.getString("director_id"))); 
                el.setNames(rs.getString("director_nombre"));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
    public List<Director> getByTitle(String title){
        
        List<Director> list = new ArrayList<Director>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT DI.DIRECTOR_ID, DI.DIRECTOR_NOMBRE FROM DIRECTORES DI "
                    + "INNER JOIN TITULOS_DIRECTORES TD ON DI.DIRECTOR_ID = TD.DIRECTOR_ID "
                    + "WHERE TD.TITULO_ID = " + title;
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Director el = new Director();
                el.setId(Integer.parseInt(rs.getString("director_id"))); 
                el.setNames(rs.getString("director_nombre"));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
}
