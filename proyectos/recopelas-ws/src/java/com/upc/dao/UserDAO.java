/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import com.upc.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leo
 */
public class UserDAO {
    

    public User get(String mail, String password) throws SQLException {
        
        Connection conn = null;
        User user = new User();

        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            String query = "SELECT * FROM USUARIOS WHERE USUARIO_CORREO = ? AND USUARIO_PASSWORD= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, mail);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                user.setEmail(rs.getString("usuario_correo"));
                user.setName(rs.getString("usuario_nombre"));                
                user.setLastnameFather(rs.getString("usuario_apellido_paterno"));
                user.setLastnameMother(rs.getString("usuario_apellido_materno"));
            } 
  
        }catch (SQLException e){
            throw e;
        }
        return user;
    }
    
    public void save(User user) throws SQLException {
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
            String query = "INSERT INTO USUARIOS (USUARIO_NOMBRE, USUARIO_APELLIDO_PATERNO, USUARIO_APELLIDO_MATERNO, USUARIO_DNI, "
                    + "USUARIO_PASSWORD, USUARIO_CORREO, USUARIO_CELULAR, USUARIO_TELEFONO, USUARIO_DIRECCION, USUARIO_TIPO, USUARIO_ESTADO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastnameFather());
            ps.setString(3, user.getLastnameMother());
            ps.setString(4, user.getDocument());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getPhone());            
            ps.setString(8, user.getPhone());
            ps.setString(9, user.getAddress());
            ps.setString(10, "normal");
            ps.setString(11, "1");
            ps.execute();
             
        }catch (SQLException e){
            throw e;
        }
        
        
    }
    
        
    
}
