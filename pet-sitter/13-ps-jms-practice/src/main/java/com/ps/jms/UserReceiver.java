package com.ps.jms;

import com.ps.ents.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class UserReceiver implements MessageListener {

    private static AtomicInteger id = new AtomicInteger();

    @Autowired
    private MessageConverter messageConverter;

    @Autowired
    private ConfirmationSender confirmationSender;

    @Override
    public void onMessage(Message message) {
        try {
            User receivedUser = (User) messageConverter.fromMessage(message);
            log.info(" >> Received user: " + receivedUser);
            confirmationSender.sendMessage(new Confirmation(id.incrementAndGet(), "User " + receivedUser.getEmail() + " received."));
        } catch (JMSException e) {
            log.error("Something went wrong ...", e);
        }

    }
}
