package com.ps.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class ProgramaticUserService2Test {

    @Autowired
    @Qualifier("programaticUserService2")
    UserService userService;

    @Before

    public void setUp() {
        assertNotNull(userService);
    }


    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void updatePassword() throws MailSendingException {
        int res = userService.updatePassword(2L, "test_pass");
        assertEquals(0, res);
    }

}
