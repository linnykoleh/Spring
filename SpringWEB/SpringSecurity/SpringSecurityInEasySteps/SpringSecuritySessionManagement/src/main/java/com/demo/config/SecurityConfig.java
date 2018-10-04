package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.demo.config")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        final JdbcDaoImpl jdbcUserDetailService = new JdbcDaoImpl();
        String authQuery = "select username,role from user_roles where username=?";
        String userQuery = "select username,password,enabled from users where username=?";
        jdbcUserDetailService.setAuthoritiesByUsernameQuery(authQuery);
        jdbcUserDetailService.setDataSource(dataSource);
        jdbcUserDetailService.setUsersByUsernameQuery(userQuery);
        return jdbcUserDetailService;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .regexMatchers("/chief/.*").hasRole("CHIEF")
                .regexMatchers("/agent/.*").access("hasRole('AGENT') and principal.name='James Bond'")
                .antMatchers("/signup**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().requiresChannel().anyRequest().requiresSecure();

        http.formLogin().loginPage("/login").permitAll();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1).expiredUrl("/login")
                .and().invalidSessionUrl("/login") // If specified, logout().delete-cookies("JSESSIONID") should be specified.
                .sessionFixation().changeSessionId();

        http.logout().deleteCookies("JSESSIONID").invalidateHttpSession(true);
    }

}
