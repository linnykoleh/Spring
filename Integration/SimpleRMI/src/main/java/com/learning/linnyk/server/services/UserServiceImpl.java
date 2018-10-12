package com.learning.linnyk.server.services;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public String getUser() {
		return "Test user";
	}
}
