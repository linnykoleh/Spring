package com.ps.jms.config;

import com.ps.jms.ConfirmationReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@Import(JmsCommonConfig.class)
public class JmsProducerConfig {

    private final JmsCommonConfig jmsCommonConfig;

    @Autowired
    public JmsProducerConfig(JmsCommonConfig jmsCommonConfig) {
        this.jmsCommonConfig = jmsCommonConfig;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        final JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsCommonConfig.connectionFactory());
        jmsTemplate.setDefaultDestination(jmsCommonConfig.userQueue());
        return jmsTemplate;
    }

    @Bean
    public ConfirmationReceiver confirmationReceiver() {
        return new ConfirmationReceiver();
    }

    @Bean
    public DefaultMessageListenerContainer containerListener() {
        final DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
        listener.setConnectionFactory(jmsCommonConfig.connectionFactory());
        listener.setDestination(jmsCommonConfig.confirmationQueue());
        listener.setMessageListener(confirmationReceiver());
        return listener;
    }
}