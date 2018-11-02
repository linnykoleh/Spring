package com.learning.linnyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DiscoveryServerApplication {

	/**
	 * - http://localhost:8888/config-client-development.properties
	 *
	 * - http://localhost:8888/config-client-development.json
	 */

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}
}
