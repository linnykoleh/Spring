package com.example.methodsecurity.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authorities")
@Data
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String email;
	private String password;

	@ManyToMany(mappedBy = "users")
	private List<Authority> authorities = new ArrayList<>();

	public User(String email, String password, Set<Authority> authorities) {
		this.email = email;
		this.password = password;
		this.authorities.addAll(authorities);
	}

	public User(String email, String password) {
		this(email, password, new HashSet<>());
	}

	public User(String email, String password, Authority... authorities) {
		this(email, password, new HashSet<>(Arrays.asList(authorities)));
	}

}
