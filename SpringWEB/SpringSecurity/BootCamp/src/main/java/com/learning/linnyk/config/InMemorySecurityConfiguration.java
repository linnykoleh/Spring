package com.learning.linnyk.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class InMemorySecurityConfiguration {

	@Bean
	UserDetailsService memoryManager() {
		return new InMemoryUserDetailsManager();
	}

	@Bean
	InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
		return () -> {
			final UserDetails oleh = User.withDefaultPasswordEncoder().username("oleh").password("password").roles("USER").build();
			userDetailsManager.createUser(oleh);
		};
	}

}
