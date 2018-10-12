package com.learning.linnyk.server;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.server.config.RmiServerConfig;

public class Server {

	public static void main(String[] args) throws IOException {
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RmiServerConfig.class);
		System.out.println("RMI reward network server started.");
		System.in.read();
		ctx.close();
	}
}
