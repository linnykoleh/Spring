package com.ps.remoting.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ps.config.ServiceConfig;

public class RmiExporterBootstrap {

	public static void main(String[] args) throws Exception {
		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RmiServerConfig.class, ServiceConfig.class);
		System.out.println("RMI reward network server started.");
		System.in.read();
		ctx.close();
	}
}
