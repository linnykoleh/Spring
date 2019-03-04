package com.learning.linnyk.lmi.java._1_version_using_proxymode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppLMI {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConfigurationLMI.class);

		final SingletonService bean = context.getBean(SingletonService.class);

		bean.doIt();
		//SingletonService: 502800944
		//PrototypeService: 572593338

		bean.doIt();
		//SingletonService: 502800944
		//PrototypeService: 384294141

		bean.doIt();
		//SingletonService: 502800944
		//PrototypeService: 1024597427
	}
}
