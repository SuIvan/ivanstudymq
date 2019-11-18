package com.ivan.activemq.queue;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * this is the mq produce
 */
@Service
public class SpringMQ_Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Produce produce = (SpringMQ_Produce) ctx.getBean("springMQ_Produce");
        produce.jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
               TextMessage message =  session.createTextMessage("*********spring整合activeMq");
                return message ;
            }
        });
    }
}
