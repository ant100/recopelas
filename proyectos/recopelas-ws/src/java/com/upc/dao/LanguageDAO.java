/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.entity.Language;
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
public class LanguageDAO {
    
    public List<Language> getByTitle(String title){
        
        List<Language> list = new ArrayList<Language>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            Statement stmt = (Statement) conn.createStatement();
            String query = "SELECT ID.IDIOMA_ID, ID.IDIOMA_NOMBRE FROM IDIOMAS ID "
                    + "INNER JOIN TITULOS_IDIOMAS TI ON ID.IDIOMA_ID = TI.IDIOMA_ID "
                    + "WHERE TI.TITULO_ID = " + title;
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Language el = new Language();
                el.setId(Integer.parseInt(rs.getString("idioma_id"))); 
                el.setName(rs.getString("idioma_nombre"));
                list.add(el);
            }
            conn.close();
            return list;
            
        }catch (SQLException e){
            return null;
        }
    }
    
}
