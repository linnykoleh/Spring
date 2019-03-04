package com.learning.linnyk.lmi.java._3_version_using_lookup_annotation_and_abstract;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(LMIConfiguration.class);
		final CommandManager commandManager = context.getBean(CommandManager.class);

		commandManager.process();
		// CommandManager: 789653861
		// Command: 1318180415

		commandManager.process();
		// CommandManager: 789653861
		// Command: 222511810

		commandManager.process();
		// CommandManager: 789653861
		// Command: 733943822
	}
}
