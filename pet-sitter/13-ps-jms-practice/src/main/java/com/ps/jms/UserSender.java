package com.ps.jms;

import com.ps.ents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Session;

@Component
public class UserSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(final User user) {
        jmsTemplate.send((Session session) -> session.createObjectMessage(user));
    }

}
