package com.ivan.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 */
public class JmsProduce {

    private static final String ACTIVE_URL = "nio://192.168.0.102:61608";
    private static final String QUEUE_NAME = "queue01";
    public static void main(String[] args) throws JMSException {
        //1.create the factory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2.create the connection
        Connection connection = factory.createConnection();
//        connection.start();
        //3.get the session
       Session session =  connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
       //create 目的地
       Queue queue = session.createQueue(QUEUE_NAME);
       //create producer
        MessageProducer messageProducer =   session.createProducer(queue);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //send 3 message to mq
        for (int i=0;i<3;i++){
            TextMessage textMessage =session.createTextMessage("msg- - -" + i);
            messageProducer.send(textMessage);
        }
        //close the source
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("all function finished");
    }
}
