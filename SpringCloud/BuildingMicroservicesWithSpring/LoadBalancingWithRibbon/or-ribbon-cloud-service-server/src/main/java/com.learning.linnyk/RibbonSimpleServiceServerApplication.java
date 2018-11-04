package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RibbonSimpleServiceServerApplication {

    @Value("${server.port}")
    public String port;

    @RequestMapping("/service")
    public String execute() {
        return "Hello from the port: " + port;
    }

    @RequestMapping("/")
    public String status() {
        return "UP";
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonSimpleServiceServerApplication.class, args);
    }
}
