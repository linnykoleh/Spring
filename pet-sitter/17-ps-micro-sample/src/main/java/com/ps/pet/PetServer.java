package com.ps.pet;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(PetServiceConfig.class)
public class PetServer {

	public static void main(String[] args) throws IOException {
		// Tell server to look for pet-server.properties or pet-server.yml
		System.setProperty("spring.config.name", "pet-server");

		final ConfigurableApplicationContext ctx = SpringApplication.run(PetServer.class, args);
		log.info("###### pet-server started ...");

		System.in.read();
		ctx.close();
	}
}
