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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.linnyk.restfulwebservices.user.exception.UserNotFoundException;
import com.learning.linnyk.restfulwebservices.user.model.Post;
import com.learning.linnyk.restfulwebservices.user.model.User;

@RestController
public class UserJPAController {

	private final UserJPAService userJPAService;

	@Autowired
	public UserJPAController(UserJPAService userJPAService) {
		this.userJPAService = userJPAService;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUsers() {
		return userJPAService.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		final User user = userJPAService.findOne(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("There is no user with id: " + id);
		}

		final Resource<User> resource = new Resource<>(user);
		final ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		final User savedUser = userJPAService.save(user);

		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedUser);
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userJPAService.deleteById(id);
	}

	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id) {
		final User user = userJPAService.findOne(id);
		return user.getPosts();
	}

	@RequestMapping(path = "/jpa/users/{id}/posts", method = RequestMethod.POST)
	public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
		final User user = userJPAService.findOne(id);

		if (Objects.isNull(user)) {
			throw new UserNotFoundException("There is no user with id: " + id);
		}

		post.setUser(user);
		userJPAService.save(post);

		final URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).body(post);
	}

}
