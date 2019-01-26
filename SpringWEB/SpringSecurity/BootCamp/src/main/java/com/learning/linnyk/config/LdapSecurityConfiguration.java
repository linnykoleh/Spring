package com.learning.linnyk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(100000)
public class LdapSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic();

        http
            .authorizeRequests().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .ldapAuthentication()
             .userDnPatterns("uid={0},uo=people")
             .groupSearchBase("ou=groups")
             .contextSource()
                .url("ldap://127.0.0.1:8389/dc=springframework,dc=org")
             .and()
                .passwordCompare()
                    .passwordAttribute("userPassword")
                    .passwordEncoder(new LdapShaPasswordEncoder());
    }
}
