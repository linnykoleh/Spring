package com.learning.linnyk.restfulwebservices.hello_world;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private final MessageSource messageSource;

	@Autowired
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}

	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World " + name);
	}

	@GetMapping(path = "/hello-world-internationalized")
	public String retrieveAllUsersInternationalized(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("message", null, locale);
	}
}
