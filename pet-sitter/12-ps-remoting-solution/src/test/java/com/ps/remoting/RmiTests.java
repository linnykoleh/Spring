package com.ps.remoting;

import com.ps.ents.User;
import com.ps.remoting.config.HttpInvokerClientConfig;
import com.ps.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test for the HTTP RMI client
 */
@ContextConfiguration(classes = HttpInvokerClientConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RmiTests {

    @Autowired
    private UserService userService;

    public void setUp() {
        assertNotNull(userService);
    }

    @Test
    public void testRmiAll() {
        final List<User> users = userService.findAll();
        assertEquals(5, users.size());
    }

    @Test
    public void testRmiJohn() {
        final User user = userService.findByEmail("John.Cusack@pet.com");
        assertNotNull(user);
    }
}
