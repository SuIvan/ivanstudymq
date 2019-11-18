package com.ivan.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;


public class JmsConsumer {
    private static final String ACTIVE_URL = "nio://192.168.0.102:61608";
    private static final String QUEUE_NAME = "queue01";
    public static void main(String[] args) throws JMSException, IOException {
        //1.create the factory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_URL);
        //2.create the connection
        Connection connection = factory.createConnection();
        connection.start();
        //3.get the session
        Session session =  connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        //create 目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
//        while(true){
//            TextMessage textMessage = (TextMessage) messageConsumer.receive();
//            if (null != textMessage){
//                System.out.println("**** producer received the message:"+textMessage.getText());
//            }else {
//                break;
//            }
//        }
//        messageConsumer.close();
//        session.close();
//        connection.close();
//
//        System.out.println("all function finished");

        messageConsumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                if (null != message && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        textMessage.acknowledge();
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
