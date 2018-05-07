/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.DirectorDAO;
import com.upc.entity.Director;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leo
 */
@Path("director")
public class DirectorREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DirectorREST
     */
    public DirectorREST() {
    }
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Director> findAll() {
        DirectorDAO directorDAO = new DirectorDAO();
        return directorDAO.getAll();
    }
}
