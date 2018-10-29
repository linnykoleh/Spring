package com.learning.linnyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServiceApplication {

    /**

     1.
         gateway-service --> routing-goodbye-service
         http://localhost:8080/goodbye

         gateway-service route requests to services by api
         g. spring.application.name=goodbye this application name of routing-goodbye-service

     2.
         gateway-service --> routing-hello-service
         http://localhost:8080/hello

         gateway-service route requests to services by api
         e.g. spring.application.name=hello this application name of routing-hello-service
    */

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
