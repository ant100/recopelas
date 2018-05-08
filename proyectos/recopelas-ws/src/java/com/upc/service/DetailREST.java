/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.dao.TitleDAO;
import com.upc.entity.Title;
import com.upc.entity.Yala;
import com.upc.model.Interest;
import com.upc.model.Response;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author leo
 */
@Path("detail")
public class DetailREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TitleREST
     */
    public DetailREST() {
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Title find(@PathParam("id") String id) {
        TitleDAO titleDAO = new TitleDAO();
        return titleDAO.get(id);
    }

}
