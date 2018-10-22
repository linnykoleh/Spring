package com.oreilly.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@ImportResource("integration-context.xml")
public class SpringIntegrationDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("Hello world");
    }
}