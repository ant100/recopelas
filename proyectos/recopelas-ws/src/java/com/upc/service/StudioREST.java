/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.StudioDAO;
import com.upc.entity.Studio;
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
@Path("studio")
public class StudioREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StudioREST
     */
    public StudioREST() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Studio> findAll() {
        StudioDAO studioDAO = new StudioDAO();
        return studioDAO.getAll();
    }
 
}
