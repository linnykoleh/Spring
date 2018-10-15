package com.ps.jms;

import com.ps.jms.config.JmsCommonConfig;
import com.ps.jms.config.JmsConsumerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class UserConsumerApp {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(
                JmsCommonConfig.class, JmsConsumerConfig.class);

        UserReceiver userReceiver = context.getBean(UserReceiver.class);
        assertNotNull(userReceiver);
        log.info("Waiting for user ...");
        System.in.read();
        context.close();
    }
}
