package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RefreshScope // In order to refresh properties and beans `POST http://localhost:8080/refresh`
public class ConfigClientApplication {

	@Value("${message}")
	private String message;

	public static void main(String[] args) {
		SpringApplication.run(ConfigClientApplication.class, args);
	}

	/**
	 * http://localhost:8080/message
	 */
	@RequestMapping("/message")
	public String message() {
		return this.message;
	}

}
