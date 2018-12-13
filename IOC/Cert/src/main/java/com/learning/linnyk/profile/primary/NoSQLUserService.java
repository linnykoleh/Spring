package com.learning.linnyk.profile.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
@Primary
public class NoSQLUserService implements UserService {

	@Override
	public String toString() {
		return "NoSQLUserService";
	}
}
