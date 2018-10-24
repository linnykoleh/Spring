package com.learning.linnyk.server;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.server.config.ServerJmxConfig;

public class Server {

	public static void main(String[] args) throws IOException {
		new AnnotationConfigApplicationContext(ServerJmxConfig.class);
		System.in.read();
	}
}
