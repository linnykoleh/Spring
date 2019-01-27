package com.example.passwordmigration;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
class CustomUserDetailsService implements UserDetailsService, UserDetailsPasswordService {

	private final Map<String, UserDetails> users = new ConcurrentHashMap<>();

	public CustomUserDetailsService(Collection<UserDetails> seedUsers) {
		seedUsers.forEach(user -> this.users.put(user.getUsername(), user));
		this.users.forEach((k, v) -> log.info(k + "=" + v.getPassword()));
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (this.users.containsKey(username)) {
			return this.users.get(username);
		}
		throw new UsernameNotFoundException(String.format("couldn't find %s!", username));
	}

	@Override
	public UserDetails updatePassword(UserDetails user, String newPassword) {
		log.info("prompted to updated password for user " + user.getUsername() + " to "
				+ newPassword);

		this.users.put(user.getUsername(),
				new CustomUserDetails(user.getUsername(), newPassword, user.isEnabled(),
						user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.toArray(String[]::new)));

		return this.loadUserByUsername(user.getUsername());
	}

}
