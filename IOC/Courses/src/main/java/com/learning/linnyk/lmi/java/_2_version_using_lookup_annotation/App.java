package com.learning.linnyk.lmi.java._2_version_using_lookup_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(LMIConfiguration.class);
		final CommandManager commandManager = context.getBean(CommandManager.class);

		commandManager.process();
		// CommandManager: 1147258851
		// Command: 891095110

		commandManager.process();
	    // CommandManager: 1147258851
		// Command: 2011482127

		commandManager.process();
		// CommandManager: 1147258851
		// Command: 905735620
	}
}
