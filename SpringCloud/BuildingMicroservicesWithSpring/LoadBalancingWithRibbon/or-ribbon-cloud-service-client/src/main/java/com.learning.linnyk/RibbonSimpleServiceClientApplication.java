package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RibbonClient(name = "simple-service", configuration = RibbonServiceConfiguration.class)
public class RibbonSimpleServiceClientApplication {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/startClient")
    public String execute() {
        return restTemplate.getForObject("http://simple-service/service", String.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonSimpleServiceClientApplication.class, args);
    }
}
