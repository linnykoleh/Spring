package com.example.passwordmigration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class CustomAuthenticationApplication {

	// @Bean
	PasswordEncoder oldPasswordEncoder() {
		String md5 = "MD5";
		return new DelegatingPasswordEncoder(md5,
				Collections.singletonMap(md5, new MessageDigestPasswordEncoder(md5)));
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	CustomUserDetailsService customUserDetailsService() {
		Collection<UserDetails> users = Arrays.asList(
				new CustomUserDetails("jlong", oldPasswordEncoder().encode("password"),
						true, "USER"),
				new CustomUserDetails("rwinch", oldPasswordEncoder().encode("password"),
						true, "USER", "ADMIN"));
		return new CustomUserDetailsService(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomAuthenticationApplication.class, args);
	}

}
