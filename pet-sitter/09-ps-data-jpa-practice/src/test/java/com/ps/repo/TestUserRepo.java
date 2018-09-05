package com.ps.repo;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.PersistenceConfig;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.ps.util.RecordBuilder.buildUser;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class TestUserRepo {

    @Autowired
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
    }

    @Test
    public void testFindById() {
        List<User> johns = userRepo.findAllByUserName("john");
        assertTrue(johns.size() == 2);
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepo.findAll();
        users.forEach(System.out::println);
    }

    @Test
    public void testNoFindById() {
        User user = userRepo.findUsernameById(99L);
        assertNull(user);
    }

    @Test
    public void testCreate() {
        User diana = buildUser("diana.ross@pet.com");
        diana.setPassword("test");
        diana.setUserType(UserType.SITTER);
        diana = userRepo.save(diana);

        assertNotNull(diana.getId());
    }

    @Test
    public void testUpdate() {
        final User john = userRepo.findOneByUsername("johncusack");

        john.setPassword("newpass");

        userRepo.saveAndFlush(john);

        assertEquals("newpass", john.getPassword());

        System.out.println(john);
    }

    @Test
    public void testDelete() {
        final User gigi = userRepo.findOneByUsername("gigipedala");

        userRepo.delete(gigi.getId());
    }
}
