/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.jms;

import com.upc.entity.User;
import java.net.URISyntaxException;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.codehaus.jackson.map.ObjectMapper;




/**
 *
 * @author MANUEL
 */
public class JmsMessageListener {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws URISyntaxException, Exception{
        
    Connection connection = null;
    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
                
    connection = connectionFactory.createConnection();
    Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);        
        
    try {
            
        Queue queueUserSystem = session.createQueue("queue.UserSystem");                      
			
        MessageConsumer mensaje = session.createConsumer(queueUserSystem);
        connection.start();
        System.out.println("Buscando mensajes en la cola:");
        while (true) {                            

            ObjectMessage mensajeRecibido = (ObjectMessage) mensaje.receive();                
                            
            ObjectMapper mapper = new ObjectMapper();
            User newUserSystem = mapper.readValue((String)mensajeRecibido.getObject(), User.class);                            
                            
            if (newUserSystem instanceof User) {                          
                                
                System.out.println("Usuario recibido -> "
                                    + ", Nombre: " + newUserSystem.getName()
                                    + ", Apellidos : " + newUserSystem.getLastnameFather()+ " " + newUserSystem.getLastnameMother()
                                    + ", DNI: " + newUserSystem.getDocument()
                                    + ", Celular: " + newUserSystem.getPhone()
                                    + ", Email: " + newUserSystem.getEmail()); 
                }               
	}			
	} finally {
            if (session != null) {
		session.close();
            }
            if (connection != null) {
		connection.close();
            }
        }
        
        
    }
    
}
