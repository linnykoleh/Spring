package com.learning.linnyk.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TurbineApplication {

	/**
	 	http://localhost:8080/hystrix
	 */

	public static void main(String[] args) {
		SpringApplication.run(TurbineApplication.class, args);
	}
}
