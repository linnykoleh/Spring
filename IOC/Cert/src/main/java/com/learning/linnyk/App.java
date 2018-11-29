package com.learning.linnyk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.config.AppConfiguration;

public class App {

	public static void main(String[] args) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		System.out.println("#7 Initialized");
	}
}
