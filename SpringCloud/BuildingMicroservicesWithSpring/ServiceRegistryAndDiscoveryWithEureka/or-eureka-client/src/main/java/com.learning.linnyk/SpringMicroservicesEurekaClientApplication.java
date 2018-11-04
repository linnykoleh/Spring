package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SpringMicroservicesEurekaClientApplication {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/execute")
    public String execute() {
        return restTemplate.getForObject("http://MyClient2/serviceInfo", String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroservicesEurekaClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
