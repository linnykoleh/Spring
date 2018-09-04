package com.ps.services;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest2 {

    private UserService userService;
    private UserRepo userRepo;

    @Before
    public void setUp() {
        userRepo = mock(UserRepo.class);
        userService = new UserServiceImpl(userRepo);
    }

    @Test
    public void testFindById() {
        User u = new User();
        u.setUsername("john");
        when(userRepo.findOne(1L)).thenReturn(u);
        User user = userService.findById(1L);
        assertNotNull(user);
    }
}
