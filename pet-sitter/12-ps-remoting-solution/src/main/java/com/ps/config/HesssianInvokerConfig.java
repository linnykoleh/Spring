package com.ps.config;

import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class HesssianInvokerConfig {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Bean(name = "/hessianInvoker/userService")
    public HessianServiceExporter hessianInvokerServiceExporter() {
        HessianServiceExporter invokerService = new HessianServiceExporter();
        invokerService.setService(userService);
        invokerService.setServiceInterface(UserService.class);
        return invokerService;
    }


}