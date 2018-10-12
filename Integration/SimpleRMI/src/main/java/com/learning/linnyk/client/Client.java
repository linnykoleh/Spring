package com.learning.linnyk.client;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.client.config.RmiClientConfig;
import com.learning.linnyk.server.services.UserService;

public class Client {

	public static void main(String[] args) throws IOException {
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RmiClientConfig.class);
		final UserService bean = ctx.getBean(UserService.class);
		final String user = bean.getUser();
		System.out.println("User name is: " + user);
		System.in.read();
		ctx.close();
	}
}
