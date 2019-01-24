package com.learning.linnyk.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Commented in order not to block JDBCSecurityConfiguration
 */
//@Configuration
public class InMemorySecurityConfiguration {

//	@Bean
	UserDetailsService memoryManager() {
		return new InMemoryUserDetailsManager();
	}

//	@Bean
	InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
		return () -> {
			final UserDetails oleh = User.withDefaultPasswordEncoder().username("oleh").password("password").roles("USER").build();
			userDetailsManager.createUser(oleh);
		};
	}

}
