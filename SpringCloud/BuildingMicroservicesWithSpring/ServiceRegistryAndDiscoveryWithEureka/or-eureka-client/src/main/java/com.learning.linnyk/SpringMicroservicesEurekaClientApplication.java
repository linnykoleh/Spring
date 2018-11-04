package com.learning.linnyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringMicroservicesEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroservicesEurekaClientApplication.class, args);
    }
}
