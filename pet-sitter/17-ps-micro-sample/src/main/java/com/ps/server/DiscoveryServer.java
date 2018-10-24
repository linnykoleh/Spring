package com.ps.server;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.log4j.Log4j;

@Log4j
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServer {

	public static void main(String[] args) throws IOException {
		// Tell server to look for discovery.properties or discovery.yml
		System.setProperty("spring.config.name", "discovery");

		final ConfigurableApplicationContext ctx = SpringApplication.run(DiscoveryServer.class, args);
		log.info("###### discovery started ...");

		System.in.read();
		ctx.close();
	}

}
