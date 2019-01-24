package com.learning.linnyk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
