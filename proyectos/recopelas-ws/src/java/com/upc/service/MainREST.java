/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.ActorDAO;
import com.upc.dao.DirectorDAO;
import com.upc.dao.GenreDAO;
import com.upc.dao.StudioDAO;
import com.upc.dao.TitleDAO;
import com.upc.entity.Title;
import com.upc.model.Filter;
import com.upc.model.Interest;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leo
 */
@Path("main")
public class MainREST {

    private ActorDAO actorDAO;
    private GenreDAO genreDAO;
    private DirectorDAO directorDAO;
    private StudioDAO studioDAO;
    private TitleDAO titleDAO;

    /**
     * Creates a new instance of MainREST
     */
    public MainREST() {
        actorDAO = new ActorDAO();
        genreDAO = new GenreDAO();
        directorDAO = new DirectorDAO();
        studioDAO = new StudioDAO();
        titleDAO = new TitleDAO();
    }
     
    @GET
    @Path("filters")
    @Produces(MediaType.APPLICATION_JSON)
    public Filter filters() {
        Filter filters = new Filter();
        filters.setActors(actorDAO.getAll());
        filters.setGenres(genreDAO.getAll());
        filters.setDirectors(directorDAO.getAll());
        filters.setStudios(studioDAO.getAll());
        return filters;
    }
 
    @POST
    @Path("recommendation")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Title> recommendation(Interest interest) {
        return titleDAO.gerRecommended(interest);
    }    
    
}
