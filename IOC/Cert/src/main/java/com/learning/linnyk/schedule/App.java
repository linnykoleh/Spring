package com.learning.linnyk.schedule;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.schedule.config.SpringConfig;

public class App {

	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(SpringConfig.class);
	}
}
