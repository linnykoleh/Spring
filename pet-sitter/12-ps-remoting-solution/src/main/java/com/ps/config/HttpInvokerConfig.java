package com.ps.config;

import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
@Import({ServiceConfig.class})
public class HttpInvokerConfig {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Bean(name = "/httpInvoker/userService")
    public HttpInvokerServiceExporter httpInvokerServiceExporter() {
        final HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
        invokerService.setService(userService);
        invokerService.setServiceInterface(UserService.class);
        return invokerService;
    }

}