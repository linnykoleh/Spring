package com.learning.linnyk;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/book/search")
public class BookController {

	@RequestMapping("/title")
	public String findByTitle(@Param("title") String title) throws URISyntaxException {
		final RestTemplate template = new RestTemplate();
		URI uri = new URI("http://localhost:9999/book/search/title?title=" + title);
		return template.getForObject(uri, String.class);
	}

}
