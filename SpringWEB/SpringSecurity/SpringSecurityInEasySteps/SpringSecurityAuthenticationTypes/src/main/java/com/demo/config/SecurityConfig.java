package com.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.demo.config")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final DataSource dataSource;

	@Autowired
	public SecurityConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public CustomUserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// inMemoryAuthentication
		auth.inMemoryAuthentication()
				.withUser("ankidaemon").password("password").roles("USER")
				.and()
				.withUser("test").password("test").roles("USER");

		// jdbcAuthentication
		auth.jdbcAuthentication()
				.dataSource(dataSource)
		  		.usersByUsernameQuery(
		   			"select username,password,enabled from users where username=?")
		  		.authoritiesByUsernameQuery(
		   			"select username,role from user_roles where username=?");
	}


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.regexMatchers("/chief/.*").hasRole("CHIEF")
				.regexMatchers("/agent/.*").access("hasRole('AGENT') and principal.name='James Bond'")
				.anyRequest().authenticated()
				.and()
					.httpBasic()
				.and()
					.requiresChannel().anyRequest().requiresSecure();
				
		http.formLogin().loginPage("/login").permitAll();
	}
		
}
