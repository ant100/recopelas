/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.utilities;

import com.upc.entity.UserSystem;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author MANUEL
 */
public class SystemUserQueue {
    
    
    
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private String subject = "queue.UserSystem"; //Nombre de la cola
    
    public void Add(UserSystem newUserSystem)throws JMSException, IOException{
    
        //Conexión
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();        

        //Sesión
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue(subject);
        
        MessageProducer producer = session.createProducer(destination);
        

        ObjectMessage message = session.createObjectMessage();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newUserSystem);
        message.setObject(json);        
        
        //Creamos un ID
        Random random = new Random(System.currentTimeMillis());
        String correlationId = UUID.randomUUID().toString();
        

        //Asignamos el ID al mensaje
        message.setJMSCorrelationID(correlationId); 
        //Enviamos el mensaje
        producer.send(message);
        //cerramos la conexión   
        connection.close();         
        
        
    }    
    
    
    
}
