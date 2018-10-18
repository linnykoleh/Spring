package com.ps.web;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriTemplate;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.exs.UserException;
import com.ps.services.UserService;

@RestController
public class RestUserController {

	private final UserService userService;

	@Autowired
	public RestUserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> all() {
		return userService.findAll();
	}

	@PostMapping
	@RequestMapping(value = "/users")
	public void create(@RequestBody @Valid User newUser, @Value("#{request.requestURL}")
			StringBuffer originalUrl, HttpServletResponse response) throws UserException {
		if (newUser.getId() != null) {
			throw new UserException("User found with username " + newUser.getUsername() + ". Cannot create!");
		}
		final User user = userService.create(newUser);
		response.setHeader("Location", getLocationForUser(originalUrl, user.getUsername()));
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/{$username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public User getByUsername(@PathVariable("$username") String username) throws UserException {
		final User user = userService.findByUsername(username);
		if (user == null) {
			throw new UserException("User not found with username " + username);
		}
		return user;
	}

	/**
	 * Determines URL of user resource based on the full URL of the given request,
	 * appending the path info with the given childIdentifier using a UriTemplate.
	 */
	private static String getLocationForUser(StringBuffer url, Object childIdentifier) {
		final UriTemplate template = new UriTemplate(url.toString() + "/{$username}");
		return template.expand(childIdentifier).toASCIIString();
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/users/{$username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User update(@PathVariable("$username") String username, @RequestBody User newUser) throws UserException {
		final User user = userService.findByUsername(username);
		if (user == null) {
			throw new UserException("User not found with username " + username);
		}
		user.setEmail(newUser.getEmail());
		user.setRating(newUser.getRating());
		userService.update(user);
		return user;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/users/{$username}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("$username") String username) throws UserException {
		final User user = userService.findByUsername(username);
		if (user == null) {
			throw new UserException("No user found for username " + username);
		}
		userService.deleteById(user.getId());
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/allusers/{$type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<User> getByType(@PathVariable("$type") UserType type) throws UserException {
		final Collection<User> result = userService.findByType(type);
		if (result == null || result.isEmpty()) {
			throw new UserException("No user found for type " + type);
		}
		return result;
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/emails/{$type}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<String> getIdsByType(@PathVariable("$type") UserType type) throws UserException {
		Collection<String> result = userService.getEmailsByType(type);
		if (result == null || result.isEmpty()) {
			throw new UserException("No user found for type " + type);
		}
		return result;
	}

	/**
	 * Maps IllegalArgumentExceptions to a 404 Not Found HTTP status code.
	 */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalArgumentException.class})
    public void handleNotFound() {
        // just return empty 404
    }

    /**
	 * Maps DataIntegrityViolationException to a 409 Conflict HTTP status code.
	 */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void handleAlreadyExists() {
        // just return empty 409
    }

}
