package com.learning.linnyk.client;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.client.config.ClientJmxConfig;
import com.learning.linnyk.server.beans.IMBeanExample;

public class Client {

	public static void main(String[] args) throws IOException {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ClientJmxConfig.class);
		final IMBeanExample bean = context.getBean(IMBeanExample.class);
		bean.setNumberPerPage(5);
		System.out.println(bean.getNumberPerPage()); //5

		System.in.read();
	}
}
