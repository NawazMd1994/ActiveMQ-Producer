package com.imaginnovate.activemqproducer.producer;


import org.apache.qpid.jms.JmsConnectionFactory;

import javax.jms.*;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class producer {

    public static void main (String []args) throws Exception{

        // Create a connection to ActiveMQ JMS broker using amqp Protocol
        JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
        Connection connection = factory.createConnection("admin", "password");
        connection.start();

        // Create a session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue
        Destination destination = session.createQueue("MyFirstQueue");

        // Create a producer specific to queue
        MessageProducer producer = session.createProducer(destination);

        Scanner input = new Scanner(System.in);
        String response;
        JSONObject obj = new JSONObject();

        obj.put("product_id", "66");
        obj.put("product_name","smart tv");
        obj.put("quantity","225");
        obj.put("price","25675");
       // System.out.println(obj);
        response = obj.toString();
        System.out.println(response);
        if(response != null){
            // Create a message object
           TextMessage msg = session.createTextMessage(response);
            // Send the message to the queue
            producer.send(msg);
        } else{
            input.close();
        }
//        do {
//            System.out.println("Queue Message is : ");
//            response = input.nextLine();
//            //response = obj.toString();
//            // Create a message object
//            TextMessage msg = session.createTextMessage(response);
//
//            // Send the message to the queue
//            producer.send(msg);
//
//
//        } while (!response.equalsIgnoreCase("Quit"));
//        input.close();

        // Close the connection
        connection.close();

    }
}
