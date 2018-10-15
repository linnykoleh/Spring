package com.ps.jms.config;

import com.ps.jms.UserReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@Import(JmsCommonConfig.class)
public class JmsConsumerConfig {

    private final JmsCommonConfig jmsCommonConfig;

    @Autowired
    public JmsConsumerConfig(JmsCommonConfig jmsCommonConfig) {
        this.jmsCommonConfig = jmsCommonConfig;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        final JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsCommonConfig.connectionFactory());
        jmsTemplate.setDefaultDestination(jmsCommonConfig.confirmationQueue());
        return  jmsTemplate;
    }

    @Bean
    public UserReceiver userReceiver(){
        return new UserReceiver();
    }

    @Bean
    public DefaultMessageListenerContainer containerListener() {
        DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
        listener.setConnectionFactory(jmsCommonConfig.connectionFactory());
        listener.setDestination(jmsCommonConfig.userQueue());
        listener.setMessageListener(userReceiver());
        return listener;
    }
}