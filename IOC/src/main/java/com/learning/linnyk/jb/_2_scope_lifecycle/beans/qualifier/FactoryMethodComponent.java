package com.learning.linnyk.jb._2_scope_lifecycle.beans.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FactoryMethodComponent {

	@Bean
	@Qualifier("public")
	public TestBean publicInstance() {
		return new TestBean("publicInstance");
	}

	@Bean
	private TestBean privateInstance() {
		return new TestBean("privateInstance");
	}

	@Bean
	protected TestBean protectedInstance(@Qualifier("public") TestBean spouse) {
		TestBean tb = new TestBean("protectedInstance");
		return tb;
	}

}