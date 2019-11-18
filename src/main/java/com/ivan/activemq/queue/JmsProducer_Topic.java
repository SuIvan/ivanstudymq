package com.ivan.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer_Topic {
    private static final String ACTIVE_URL = "tcp://192.168.0.102 :61616";
    private static final String TOPIC_NAME = "queue01";
    public static void main(String[] args) throws JMSException {
        //1.create the factory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2.create the connection
        Connection connection = factory.createConnection();

        //3.get the session
        Session session =  connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //create 目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //create producer
        MessageProducer messageProducer =   session.createProducer(topic);
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
