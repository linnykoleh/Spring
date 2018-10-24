package com.ps.user;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(UserServiceConfig.class)
public class UserServer {

	public static void main(String[] args) throws IOException {
		// Tell server to look for user-server.properties or user-server.yml
		System.setProperty("spring.config.name", "user-server");

		ConfigurableApplicationContext ctx = SpringApplication.run(UserServer.class, args);
		log.info("###### user-server started ...");

		System.in.read();
		ctx.close();
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
