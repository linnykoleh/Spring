package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.demo.config")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;
    private final CustomDigestAuthenticationEntryPoint customDigestAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint, CustomDigestAuthenticationEntryPoint customDigestAuthenticationEntryPoint) {
        this.customBasicAuthenticationEntryPoint = customBasicAuthenticationEntryPoint;
        this.customDigestAuthenticationEntryPoint = customDigestAuthenticationEntryPoint;
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
                .withUser("ankidaemon").password("password").roles("USER")
                .and()
                .withUser("test").password("test").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().regexMatchers("/chief/.*").hasRole("USER")
				.regexMatchers("/agent/.*").access("hasRole('AGENT') and principal.name='James Bond'").anyRequest()
				.authenticated()
				
//				.and().httpBasic().authenticationEntryPoint(customBasicAuthenticationEntryPoint)
				.and().exceptionHandling().authenticationEntryPoint(customDigestAuthenticationEntryPoint)
				
				.and().requiresChannel().anyRequest().requiresSecure()
				
//				.and().addFilter(basicAuthenticationFilter(super.authenticationManagerBean()));
				.and().addFilter(digestAuthenticationFilter());

		http.formLogin().loginPage("/login").permitAll();
	}

	private DigestAuthenticationFilter digestAuthenticationFilter() {
		final DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
		digestAuthenticationFilter.setAuthenticationEntryPoint(customDigestAuthenticationEntryPoint);
		digestAuthenticationFilter.setUserDetailsService(userDetailsService());
		return digestAuthenticationFilter;
	}

	public BasicAuthenticationFilter basicAuthenticationFilter(AuthenticationManager authManager) {
		final BasicAuthenticationFilter basicAuthenticationFilter =
                new BasicAuthenticationFilter(authManager, customBasicAuthenticationEntryPoint);
		return basicAuthenticationFilter;
	}
}
