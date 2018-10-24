package com.ps.web;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner, needed because of the microservices we need access to
public class WebServer {

	private static final String USERS_SERVICE_URL = "http://USERS-SERVICE";
	private static final String PETS_SERVICE_URL = "http://PETS-SERVICE";

	public static void main(String[] args) throws IOException {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");

		ConfigurableApplicationContext ctx = SpringApplication.run(WebServer.class, args);
		log.info("###### pet-server started ...");

		System.in.read();
		ctx.close();
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public AllWebService allWebService() {
		return new AllWebService(USERS_SERVICE_URL, PETS_SERVICE_URL);
	}

	@Bean
	public AllWebController allController() {
		return new AllWebController(allWebService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}

}
