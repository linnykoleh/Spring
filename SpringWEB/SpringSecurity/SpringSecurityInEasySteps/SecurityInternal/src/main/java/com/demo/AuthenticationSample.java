package com.demo;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class AuthenticationSample {

	private static AuthenticationManager authenticationManager = new AuthenticationManagerImpl();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Username: ");
		String name = sc.nextLine();
		System.out.println("Please enter Password: ");
		String password = sc.nextLine();

		try {
			Authentication request = new UsernamePasswordAuthenticationToken(name, password);
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);

		}
		catch (AuthenticationException e) {
			System.out.println("Authentication failed: " + e.getMessage());
			System.exit(1);
		}

		System.out.println("Successfully authenticated. Security context contains: "
				+ SecurityContextHolder.getContext().getAuthentication());
	}

}

class AuthenticationManagerImpl implements AuthenticationManager {

	private UserDetailsService userDetailsService() {
		final InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("ankidaemon").password("password").roles("ADMIN").build());
		manager.createUser(User.withUsername("test").password("password").roles("USER").build());
		return manager;
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		final UserDetails user = userDetailsService().loadUserByUsername(auth.getName());
		if (user != null) {
			if (user.getPassword().equals(auth.getCredentials())) {
				if (user.getUsername().equals("ankidaemon")) {
					return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),
							new ArrayList<GrantedAuthority>() {
								{
									add(new SimpleGrantedAuthority("ROLE_ADMIN"));
								}
							});
				} else {
					System.out.println("User is " + user.getUsername());
					return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(),
							new ArrayList<GrantedAuthority>() {
								{
									add(new SimpleGrantedAuthority("ROLE_USER"));
								}
							});
				}
			}
		}

		throw new BadCredentialsException("Bad Credentials");
	}
}
