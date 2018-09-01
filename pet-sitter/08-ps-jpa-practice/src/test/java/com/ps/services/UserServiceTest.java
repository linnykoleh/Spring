package com.ps.services;

import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.init.DBInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    DBInitializer initializer;

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
        assertNotNull(userService);
        initializer.initDb();
    }

    @BeforeTransaction
    public void checkDbInit() {
        long count = userService.countUsers();
        assertEquals(1, count);
    }

    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }
}
