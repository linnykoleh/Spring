package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.demo.config")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ankidaemon").password("password").roles("CHIEF")
                .and().withUser("test").password("test").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .regexMatchers("/chief/.*").hasRole("CHIEF")
                .regexMatchers("/agent/.*").access("hasRole('USER') and principal.name='James Bond'")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().requiresChannel().anyRequest().requiresSecure();

        http.formLogin().loginPage("/login").permitAll();

        http.exceptionHandling().accessDeniedPage("/accessDenied");

        http
                .logout()
                .logoutUrl("/customlogout")
                .logoutSuccessUrl("/")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .invalidateHttpSession(true) //true by default
                .addLogoutHandler(new SecurityContextLogoutHandler())
                .deleteCookies("JSESSIONID");
    }

}
