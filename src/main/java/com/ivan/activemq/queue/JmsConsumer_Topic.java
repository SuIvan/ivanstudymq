package com.ivan.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer_Topic {

    private static final String ACTIVE_URL = "tcp://192.168.0.102 :61616";
    private static final String TOPIC_NAME = "topic-name";
    public static void main(String[] args) throws JMSException, IOException {
        //1.create the factory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2.create the connection
        Connection connection = factory.createConnection();

        //3.get the session
        Session session =  connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //create 目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //create producer
        MessageConsumer messageConsumer = session.createConsumer(topic);

        messageConsumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("********* producer get the message:"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
        System.out.println("***message publish completed");
    }
}
