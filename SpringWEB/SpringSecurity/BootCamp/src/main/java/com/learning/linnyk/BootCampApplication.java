package com.learning.linnyk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BootCampApplication {

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(BootCampApplication.class, args);
	}

}

@RestController
class IsbnRestController {

	private final RestTemplate restTemplate;

	@Autowired
	IsbnRestController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/books/{isbn}")
	String lookUpBookByIsbn(@PathVariable String isbn) {
		final ResponseEntity<String> exchange =
				restTemplate.exchange("https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn, HttpMethod.GET, null, String.class);
		return exchange.getBody();
	}

}
