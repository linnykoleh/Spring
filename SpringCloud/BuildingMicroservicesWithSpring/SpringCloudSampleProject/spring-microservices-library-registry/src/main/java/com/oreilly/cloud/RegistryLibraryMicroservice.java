package com.oreilly.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryLibraryMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(RegistryLibraryMicroservice.class, args);
	}
}
