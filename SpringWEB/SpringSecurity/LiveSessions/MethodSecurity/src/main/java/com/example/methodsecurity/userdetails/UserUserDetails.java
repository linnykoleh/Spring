package com.example.methodsecurity.userdetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.methodsecurity.entities.User;

public class UserUserDetails implements UserDetails {

	private final User user;
	private final Set<GrantedAuthority> authorities;

	public UserUserDetails(User user) {
		this.user = user;
		this.authorities = this.user.getAuthorities().stream()
				.map(au -> new SimpleGrantedAuthority("ROLE_" + au.getAuthority()))
				.collect(Collectors.toSet());
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}