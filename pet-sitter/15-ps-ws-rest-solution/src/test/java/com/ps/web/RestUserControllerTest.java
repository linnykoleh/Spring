package com.ps.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ps.base.UserType;
import com.ps.ents.User;

public class RestUserControllerTest {

    /**
     * Rest Services endpoints
     */
    private static final String GET_POST_URL = "http://localhost:8080/15-ps-ws-rest-solution/users";
    private static final String GET_PUT_DEL_URL = "http://localhost:8080/15-ps-ws-rest-solution/users/{$username}";
    private static final String GET_EMAILS_BY_TYPE_URL = "http://localhost:8080/15-ps-ws-rest-solution/emails/{$type}";

    private RestTemplate restTemplate = null;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    //Test GET all
    @Test
    public void getAll() {
        final User[] users = restTemplate.getForObject(GET_POST_URL, User[].class);

        assertTrue(users.length > 0);
        for (User user : users) {
            System.out.println(user.getId() + ", " + user.getEmail() + ", " + user.getUsername());
        }
    }

    //Test GET by username
    @Test
    public void findByUsername() {
        final User user = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "johncusack");

        assertNotNull(user);
        assertEquals("John.Cusack@pet.com", user.getEmail());
        assertEquals(UserType.OWNER, user.getUserType());
    }

    @Test(expected = HttpClientErrorException.class)
    public void findByUsernameNonExistent() {
        final User user = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "iulianacosmina");
        assertNotNull(user);
    }

    //Test POST
    @Test
    public void createUser() {
        final User user = new User();
        user.setEmail("Doctor.Who@tardis.com");
        user.setUsername("doctorwho");
        user.setLastName("Doctor");
        user.setFirstName("Who");
        user.setRating(0d);
        user.setActive(true);
        user.setPassword("what");
        user.setUserType(UserType.ADMIN);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        final HttpEntity<User> crRequest = new HttpEntity<>(user, headers);
        final URI uri = this.restTemplate.postForLocation(GET_POST_URL, crRequest, User.class);
        System.out.println(">> Location for new user: " + uri);
        assertNotNull(uri);
        assertTrue(uri.toString().contains("doctorwho"));

        // test insertion
        final User newUser = restTemplate.getForObject(uri, User.class);

        assertNotNull(newUser);
        assertNotNull(newUser.getId());
    }

    // Test PUT
    @Test
    public void editUser() {
        final User user = new User();
        user.setEmail("MissJones@pet.com");
        user.setUsername("jessicajones");
        user.setRating(5d);
        user.setLastName("Jessica");
        user.setFirstName("Jones");
        user.setPassword("what");
        user.setUserType(UserType.ADMIN);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        final HttpEntity<User> userRequest = new HttpEntity<>(user, headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(GET_PUT_DEL_URL, HttpMethod.PUT, userRequest, User.class,
                "jessicajones");

        final User editedUser = responseEntity.getBody();
        assertNotNull(editedUser);
        assertEquals("MissJones@pet.com", editedUser.getEmail());
    }

    // Test DELETE
    @Test
    public void deleteUser() {
        restTemplate.delete(GET_PUT_DEL_URL, "gigipedala");

        // test insertion
        final User newUser = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "gigipedala");
        assertNull(newUser);
    }


    @Test
    public void getEmailsByType() {
        final String[] users = restTemplate.getForObject(GET_EMAILS_BY_TYPE_URL, String[].class, "OWNER");

        assertNotNull(users);
        assertEquals(2, users.length);
    }

}
