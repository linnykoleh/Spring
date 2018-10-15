package com.ps.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Slf4j
public class ConfirmationReceiver implements MessageListener {

    @Autowired
    private MessageConverter messageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            Confirmation receivedConf = (Confirmation) messageConverter.fromMessage(message);
            log.info(" >>  Received confirmation: " + receivedConf);
        } catch (JMSException e) {
            log.error("Something went wrong ...", e);
        }

    }
}