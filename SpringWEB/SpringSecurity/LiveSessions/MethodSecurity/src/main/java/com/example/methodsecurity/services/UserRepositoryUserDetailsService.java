package com.example.methodsecurity.services;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.methodsecurity.entities.User;
import com.example.methodsecurity.repositories.UserRepository;
import com.example.methodsecurity.userdetails.UserUserDetails;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public UserRepositoryUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(username);
		if (Objects.nonNull(user)) {
			return new UserUserDetails(user);
		} else {
			throw new UsernameNotFoundException("couldn't find " + username + "!");
		}
	}

}