package com.ps.web;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserSkeleton {

	private Long id;
	private String username;
	private List<PetSkeleton> pets;

	public UserSkeleton(Long id, String username) {
		this.id = id;
		this.username = username;
	}
}
