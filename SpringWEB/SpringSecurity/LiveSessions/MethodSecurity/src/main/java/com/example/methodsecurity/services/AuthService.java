package com.example.methodsecurity.services;

import org.springframework.stereotype.Service;

import com.example.methodsecurity.entities.Message;
import com.example.methodsecurity.entities.User;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service("authz")
public class AuthService {

	public boolean check(Message msg, User user) {
		log.info("checking " + user.getEmail() + "..");
		return msg.getTo().getId().equals(user.getId());
	}

}
