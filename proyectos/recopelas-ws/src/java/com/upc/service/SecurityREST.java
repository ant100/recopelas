/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.UserDAO;
import com.upc.model.Response;
import com.upc.model.User;
import java.sql.SQLException;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leo
 */
@Path("security")
public class SecurityREST {

    private UserDAO userDAO;
    
    public SecurityREST() {
        userDAO = new UserDAO();
    }
 
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(User user) {
        
        try{
            return userDAO.get(user.getEmail(), user.getPassword());
        }catch(SQLException e){
            return null;
        }
    }
 
    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        
        Response res = new Response();
        try{
            userDAO.save(user);
            res.setCode(1);
            res.setMessage("Guardado");
        }catch(SQLException e){
            res.setCode(0);
            res.setMessage(e.getMessage());
        }
        return res;
    }
}
