package com.learning.linnyk;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.config.AppConfiguration;
import com.learning.linnyk.services.MyClass;

public class App {

	public static void main(String[] args) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		context.getBean(MyClass.class).doIt();

		// testExecution2
		// testExecution3
		// MyClass doIt

	}
}
