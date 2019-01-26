package com.learning.linnyk.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Commented in order not to block InMemorySecurityConfiguration
 */
//@Configuration
public class JDBCSecurityConfiguration {

	@Bean
	UserDetailsService jdbcManager(DataSource dataSource) {
		final JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

	@Bean
	InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
		return () -> {
			final UserDetails oleh = User.withDefaultPasswordEncoder().username("oleh").password("password").roles("USER").build();
			userDetailsManager.createUser(oleh);
		};
	}
}
