/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.ActorDAO;
import com.upc.entity.Actor;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leo
 */
@Path("actor") 
public class ActorREST {

    @Context
    private UriInfo context;  
    /**
     * Creates a new instance of ActorREST
     */
    public ActorREST() {
    }

    /**
     * Retrieves representation of an instance of com.upc.service.ActorREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getJson() {
        ActorDAO actorDAO = new ActorDAO();
        return actorDAO.getAll();
    }

    /**
     * PUT method for updating or creating an instance of ActorREST
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
