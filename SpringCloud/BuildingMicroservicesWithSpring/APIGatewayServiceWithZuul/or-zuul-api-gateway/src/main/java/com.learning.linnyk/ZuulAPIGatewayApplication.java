package com.learning.linnyk;

import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulAPIGatewayApplication {

    /**
     * Take a loot at the properties from application.properties
     *
     * http://localhost:8080/apiV1/otherPath/execute
     */

    public static void main(String[] args) {
        SpringApplication.run(ZuulAPIGatewayApplication.class, args);
    }

    @Bean
    public ZuulFilter zuulFilter(){
        return new ORZuulFilter();
    }
}
