package com.ps.jms;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class UserReceiver {

    private static AtomicInteger id = new AtomicInteger();

    private final ConfirmationSender confirmationSender;

    @Autowired
    public UserReceiver(ConfirmationSender confirmationSender) {
        this.confirmationSender = confirmationSender;
    }

    @JmsListener(destination = "userQueue", containerFactory = "connectionFactory")
    public void receiveMessage(User receivedUser, Message message) {
        log.info(" >> Original received message: " + message);
        log.info(" >> Received user: " + receivedUser);
        confirmationSender.sendMessage(new Confirmation(id.incrementAndGet(), "User " + receivedUser.getEmail() + " received."));

    }

}
