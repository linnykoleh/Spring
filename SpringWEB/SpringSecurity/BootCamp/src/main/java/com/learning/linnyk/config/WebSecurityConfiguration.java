package com.learning.linnyk.config;

import com.learning.linnyk.services.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    public WebSecurityConfiguration(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

		http.authorizeRequests().anyRequest().authenticated();

		http
            .formLogin().permitAll();

		http
            .logout()
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    log.info("Logout callback handled for {}", authentication.getPrincipal());
                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
