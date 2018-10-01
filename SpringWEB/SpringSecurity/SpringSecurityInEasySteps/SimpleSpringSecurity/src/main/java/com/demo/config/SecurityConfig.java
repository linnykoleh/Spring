package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Authentication
     */
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ankidaemon").password("password").roles("USER")
                .and().withUser("test").password("test").roles("USER");
    }
     
/*
    Another way besides the configureGlobal to configure the authentication
    @Bean
	public UserDetailsService userDetailsService() {
		final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("ankidaemon").password("password").roles("USER").build());
		manager.createUser(User.withUsername("test").password("test").roles("USER").build());
		return manager;
	}*/

	/**
	 * Authorisation
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
                .regexMatchers("/chief/.*")
                    .hasRole("USER")// .hasAuthority("ROLE_USER")
                .regexMatchers("/agent/.*")
                    .access("hasRole('AGENT') and principal.name='James Bond'")
                    .anyRequest()
                    .authenticated()
                .and()
                    .requiresChannel().regexMatchers("/chief/*") .requiresSecure() /*Only https */
                .and()
                    .requiresChannel().regexMatchers("/admin/*").requiresInsecure();

    	http.formLogin()
    			.loginPage("/login") 
    			.permitAll();
    }

}
