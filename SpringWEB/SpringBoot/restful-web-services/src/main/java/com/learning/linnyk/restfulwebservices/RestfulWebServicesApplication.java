package com.learning.linnyk.restfulwebservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(RestfulWebServicesApplication.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            log.info(name);
        }
    }
}
