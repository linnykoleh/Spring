package com.learning.linnyk.lmi.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(LMIConfiguration.class);
		final CommandManager commandManager = context.getBean(CommandManager.class);

		commandManager.process();
		// CommandManager: 81412691
		// Command: 717176949

		commandManager.process();
	    // CommandManager: 81412691
		// Command: 1997353766

		commandManager.process();
		// CommandManager: 81412691
		// Command: 1288235781
	}
}
