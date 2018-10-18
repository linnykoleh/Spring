package com.ps.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriTemplate;

import com.ps.ents.User;
import com.ps.exs.UserException;
import com.ps.services.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/pets/{$username}", method = RequestMethod.POST)
    public ResponseEntity<String> createPet(@PathVariable("$username") String username,
                    @Value("#{request.requestURL}")
                    StringBuffer url) throws UserException {
        final User owner = userService.findByUsername(username);
        if (owner == null) {
            throw new UserException("User not found with username " + username);
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", getLocationForUser(url, owner.getUsername()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Determines URL of pet resource based on the full URL of the given request,
     * appending the path info with the given childIdentifier using a UriTemplate.
     */
    private static String getLocationForUser(StringBuffer url, Object childIdentifier) {
        final UriTemplate template = new UriTemplate(url.toString() + "/{$username}");
        return template.expand(childIdentifier).toASCIIString();
    }
}
