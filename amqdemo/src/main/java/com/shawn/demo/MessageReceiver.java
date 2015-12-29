package com.shawn.demo;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ExceptionListener;

/**
 * Created by shawn on 15/11/9.
 */
public class MessageReceiver implements MessageListener {
    @Override
    @Transactional(noRollbackFor={Throwable.class})
    public void onMessage(Message message) {
        try{
            long timestamp = System.currentTimeMillis();
            ActiveMQTextMessage textMessage = (ActiveMQTextMessage)message;
            System.out.println(textMessage.getText() /*+ " @" + timestamp*/);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
