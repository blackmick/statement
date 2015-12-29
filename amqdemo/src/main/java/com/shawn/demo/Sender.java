package com.shawn.demo;

import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

import javax.jms.*;

public class Sender{
    private JmsTemplate jmsTemplate;
    private PooledConnectionFactory connectionFactory;
    public Sender(){
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL("failover:(tcp://127.0.0.1:61616,tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)");
//        activeMQConnectionFactory.setUseAsyncSend(true);
//
//        connectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
//        connectionFactory.setMaxConnections(40);
//        connectionFactory.setIdleTimeout(0);
//        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(connectionFactory);
//
//        jmsTemplate = new JmsTemplate(singleConnectionFactory);
//        jmsTemplate.setDefaultDestinationName("amqdemo");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:sender.xml");
        jmsTemplate = (JmsTemplate)ctx.getBean("jmsTemplate");
        connectionFactory = (PooledConnectionFactory)((SingleConnectionFactory)jmsTemplate.getConnectionFactory()).getTargetConnectionFactory();
    }

    public void send(String msg){
        long start = System.currentTimeMillis();
        jmsTemplate.convertAndSend(msg);
        long end = System.currentTimeMillis();
        //System.out.println("cost:"+(end-start));
    }

    public void simpleSend(String msg) throws JMSException{
        ConnectionFactory connectionFactory = new org.apache.activemq.ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("amqdemo");
        MessageProducer mp = session.createProducer(destination);
        mp.setDeliveryMode(DeliveryMode.PERSISTENT);

        TextMessage tm = session.createTextMessage(msg);
        mp.send(tm);

        session.close();
        connection.close();
    }

    public void stop(){
        connectionFactory.stop();
    }
}