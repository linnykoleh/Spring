package com.learning.linnyk.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, UserDetails> users = new ConcurrentHashMap<>();

    public CustomUserDetailsService(Collection<UserDetails> seedUsers) {
        seedUsers.forEach(user -> users.put(user.getUsername(), user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(users.containsKey(username)){
            return users.get(username);

        }
        throw new UsernameNotFoundException("Couldn't find username: " + username);
    }
}
