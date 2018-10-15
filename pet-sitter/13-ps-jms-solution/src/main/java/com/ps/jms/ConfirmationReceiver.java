package com.ps.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmationReceiver {

    @JmsListener(destination = "confirmationQueue", containerFactory = "connectionFactory")
    public void receiveConfirmation(Confirmation confirmation) {
        log.info(" >>  Received confirmation: " + confirmation);

    }
}