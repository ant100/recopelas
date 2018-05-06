/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.TitleDAO;
import com.upc.entity.Title;
import com.upc.model.Interest;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leo
 */
@Path("title")
public class TitleREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TitleREST
     */
    public TitleREST() {
    }

    /**
     * Retrieves representation of an instance of com.upc.service.TitleREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Title> getJson() {
        TitleDAO titleDAO = new TitleDAO();
        return titleDAO.getAll();
    }

    @POST
    @Path("recommendation")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Title> recommendation(Interest interest) {
        TitleDAO titleDAO = new TitleDAO();
        System.out.println(interest.getGenres().get(0).getName());
        return titleDAO.getAll();
    }
}
