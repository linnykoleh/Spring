package com.ps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String... args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        log.info("started...");
        System.in.read();
        context.close();
    }

}
