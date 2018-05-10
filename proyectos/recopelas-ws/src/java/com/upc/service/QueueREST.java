/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.service;

import com.upc.model.User;
import com.upc.utilities.SystemUserQueue;
import java.io.IOException;
import javax.jms.JMSException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author MANUEL
 */
@Path("queue")
public class QueueREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of QueueREST
     */
    public QueueREST() {
    }

    /**
     * Retrieves representation of an instance of com.upc.service.QueueREST
     * @return an instance of java.lang.String
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void postAddToQueue(User userSystem)throws JMSException, IOException {
        
        SystemUserQueue queue = new SystemUserQueue();
        queue.Add(userSystem);
        
    }
}
