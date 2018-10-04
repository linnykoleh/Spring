package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

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

        http.exceptionHandling().accessDeniedPage("/accessDenied");

        http.formLogin().loginPage("/login").permitAll();

        http.logout().logoutUrl("/customlogout");

        //http.headers().disable();

        //http.header.defaultsDisabled();

        http.headers().cacheControl()
                .and().contentTypeOptions()
                .and().httpStrictTransportSecurity().includeSubDomains(true).maxAgeInSeconds(31536000)
                .and().httpPublicKeyPinning().includeSubDomains(true).reportUri("https://report-uri.io/") //<Reporting URL - https://report-uri.io/>.addSha256Pins("U3ByaW5nU2VjdXJpdHlJbkVhc3lTdGVwcw==") //< Base64 encoded Subject Public Key Information (SPKI) fingerprint >
                .and().xssProtection().block(false)
                .and().addHeaderWriter(new StaticHeadersWriter("Custom-Security-Header", "value"));
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().regexMatchers("/resources/.*");
    }

}
