package com.learning.linnyk.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.learning.linnyk.server.services.UserService;
import com.learning.linnyk.server.services.UserServiceImpl;

@Configuration
public class RmiServerConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public RmiServiceExporter rmiServiceExporter() {
        final RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setRegistryPort(1098);
        exporter.setAlwaysCreateRegistry(true);
        exporter.setServiceName("userService");
        exporter.setServiceInterface(UserService.class);
        exporter.setService(userService());
        return exporter;
    }

}
