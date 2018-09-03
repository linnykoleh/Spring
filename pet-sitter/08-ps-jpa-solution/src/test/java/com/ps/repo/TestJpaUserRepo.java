package com.ps.repo;

import static com.ps.util.RecordBuilder.buildUser;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.init.DBInitializer;
import com.ps.repos.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
@Transactional
public class TestJpaUserRepo {

    @Autowired
    @Qualifier("userJpaRepo")
    UserRepo userRepo;

    @Autowired
    DBInitializer initializer;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
        initializer.initDb();
    }

    @Test
    public void testFindById() {
        final List<User> johns = userRepo.findAllByUserName("johncusack", true);

        assertTrue(johns.size() == 1);
    }

    @Test
    public void testNoFindById() {
        User user = userRepo.findById(99L);
        assertNull(user);
    }

    @Test
    public void testCreate() {
        final User diana = buildUser("diana.ross@pet.com");

        diana.setPassword("test");
        diana.setUserType(UserType.SITTER);

        userRepo.save(diana);

        final List<User> dianas = userRepo.findAllByUserName("dianaross", true);

        assertTrue(dianas.size() == 1);
    }

    @Test
    public void testUpdate() {
        final List<User> johns = userRepo.findAllByUserName("johncusack", true);

        final User john = johns.get(0);

        userRepo.updatePassword(john.getId(), "newpass");
    }

    @Test
    public void testDelete() {
        final List<User> johns = userRepo.findAllByUserName("johncusack", true);

        final User john = johns.get(0);

        userRepo.deleteById(john.getId());
    }

    @Test
    public void testCriteriaBuilder(){
        final List<User> cusacks = userRepo.findAllByLastName("cusack");

        assertTrue(cusacks.size() == 1);
    }

    @After
    public void cleanUp() {
        final List<User> users = userRepo.findAll();
        
        for (User u : users) {
            userRepo.deleteById(u.getId());
        }
    }
}
