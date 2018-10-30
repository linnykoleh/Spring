package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ClientApplication {

	private final EurekaClient client;
	private final RestTemplateBuilder restTemplateBuilder;

	@Autowired
	public ClientApplication(EurekaClient client, RestTemplateBuilder restTemplateBuilder) {
		this.client = client;
		this.restTemplateBuilder = restTemplateBuilder;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@RequestMapping("/")
	public String callService() {
		final RestTemplate restTemplate = restTemplateBuilder.build();

		//virtualHostName is spring.application.name of the microservice
		final InstanceInfo instanceInfo = client.getNextServerFromEureka("service", false);

		final String baseUrl = instanceInfo.getHomePageUrl();
		final ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
		return response.getBody();
	}
}
