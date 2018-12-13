package com.learning.linnyk.profile.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(PrimaryTestConfiguration.class);

		final UserService userService = context.getBean(UserService.class);
		System.out.println(userService); //PostgreUserServiceImpl

		/*-----------------------------------*/

		final AnnotationConfigApplicationContext contextProd = new AnnotationConfigApplicationContext();
		contextProd.getEnvironment().setActiveProfiles("prod");
		contextProd.register(PrimaryTestConfiguration.class);
		contextProd.refresh();

		final UserService beanProd = contextProd.getBean(UserService.class);
		System.out.println(beanProd); //NoSQLUserService

		/*----------------------------------*/

		final AnnotationConfigApplicationContext contextNotExistProfile = new AnnotationConfigApplicationContext();
		contextNotExistProfile.getEnvironment().setActiveProfiles("NotExistProfile");
		contextNotExistProfile.register(PrimaryTestConfiguration.class);
		contextNotExistProfile.refresh();

		final UserService beanNotExistProfile = contextNotExistProfile.getBean(UserService.class);
		System.out.println(beanNotExistProfile); //PostgreUserServiceImpl
	}
}
