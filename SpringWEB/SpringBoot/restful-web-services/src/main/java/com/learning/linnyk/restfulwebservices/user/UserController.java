package com.learning.linnyk.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.linnyk.restfulwebservices.user.exception.UserNotFoundException;

@RestController
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		final User user = userService.findOne(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("There is no user with id: " + id);
		}

		final Resource<User> resource = new Resource<>(user);
		final ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		final User savedUser = userService.save(user);

		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedUser);
	}

	@DeleteMapping(path = "/users/{id}")
	public ResponseEntity<Object> createUser(@PathVariable int id) {
		final boolean result = userService.deleteById(id);

		if (!result) {
			throw new UserNotFoundException("There is no user with id: " + id);
		}
		return ResponseEntity.noContent().build();
	}

}
