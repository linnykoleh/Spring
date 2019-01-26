package com.learning.linnyk.services;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Need to be registered at WebSecurityConfigurerAdapter in a method below:
 *
 * @Override protected void configure(AuthenticationManagerBuilder auth) {
 * auth.authenticationProvider(new CustomAuthentificationProvider());
 * }
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    final List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("USER"));

    private boolean isValid(String user, String password) {
        return user.equals("oleholeh") && password.equals("password");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        if (isValid(username, password)) {
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }
        throw new BadCredentialsException("Couldn't authenticate the user: " + username + " !");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
