package com.learning.linnyk.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {

	@GetMapping("/greeting")
	String greeting(Principal principal) {
		return "hello " + principal.getName() + " !";
	}
}
