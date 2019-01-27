package com.example.passwordmigration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
class GreetingRestController {

	@GetMapping("/greeting")
	String greet(Principal p) {
		return "greetings, " + p.getName() + "!";
	}

}
