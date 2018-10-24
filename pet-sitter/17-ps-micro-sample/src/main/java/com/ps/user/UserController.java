package com.ps.user;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserRepo userRepository;

	@Autowired
	public UserController(UserRepo userRepository) {
		this.userRepository = userRepository;
	}

	@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON)
	public List<User> all() {
		List<User> users = userRepository.findAll();
		log.info("Users extracted");
		if (users.isEmpty()) {
			throw new UserNotFoundException("all");
		} else {
			return users;
		}
	}

	@RequestMapping("/id/{id}")
	public User byUsername(@PathVariable("id") Long id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		} else {
			return user;
		}
	}

	@RequestMapping("/{username}")
	public User byUsername(@PathVariable("username") String username) {
		User user = userRepository.findOneByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(username);
		} else {
			return user;
		}
	}
}
