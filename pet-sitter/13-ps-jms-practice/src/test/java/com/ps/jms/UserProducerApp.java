package com.ps.jms;

import com.ps.config.ServiceConfig;
import com.ps.ents.User;
import com.ps.jms.config.JmsCommonConfig;
import com.ps.jms.config.JmsProducerConfig;
import com.ps.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
public class UserProducerApp {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(
                ServiceConfig.class, JmsCommonConfig.class, JmsProducerConfig.class);
        UserService userService = context.getBean(UserService.class);
        UserSender userSender = context.getBean(UserSender.class);

        List<User> users = userService.findAll();

        assertTrue(users.size() > 0);
        for (User user : users) {
            userSender.sendMessage(user);
        }

        log.info("User message sent. Wait for confirmation...");

        System.in.read();

        context.close();
    }
}
