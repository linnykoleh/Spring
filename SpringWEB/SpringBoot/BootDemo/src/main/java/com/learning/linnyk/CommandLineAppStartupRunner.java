package com.learning.linnyk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) {
		System.out.println("application context has been loaded");
	}
}