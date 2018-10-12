package com.ps.remoting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.ents.User;
import com.ps.remoting.config.RmiClientConfig;
import com.ps.services.UserService;

@ContextConfiguration(classes = RmiClientConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RmiTests {

    @Autowired
    private UserService userService;

    public void setUp() {
        assertNotNull(userService);
    }

    @Test
    public void testRmiAll() {
        List<User> users = userService.findAll();
        assertEquals(5, users.size());
    }

    @Test
    public void testRmiJohn() {
        User user = userService.findByEmail("John.Cusack@pet.com");
        assertNotNull(user);
    }
}
