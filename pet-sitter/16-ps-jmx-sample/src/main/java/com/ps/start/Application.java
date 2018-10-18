package com.ps.start;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableMBeanExport;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableMBeanExport
public class Application {

	public static void main(String[] args) throws IOException {
		final ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		log.info("Started ...");
		System.in.read();
		ctx.close();
	}

}
