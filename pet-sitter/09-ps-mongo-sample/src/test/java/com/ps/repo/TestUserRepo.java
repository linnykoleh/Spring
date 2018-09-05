package com.ps.repo;

import static com.ps.util.RecordBuilder.buildUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.config.AppConfig;
import com.ps.ents.User;
import com.ps.init.DBInitializer;
import com.ps.repos.MongoDBRepo;
import com.ps.repos.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TestUserRepo {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MongoDBRepo mongoDBRepo;

    @Autowired
    private DBInitializer initializer;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
        userRepo.deleteAll();
        initializer.initDb();
    }

    @Test
    public void testFindAllByUserName() {
        final List<User> johns = userRepo.findAllByUserName("john");

        assertTrue(johns.size() == 2);
        System.out.println(johns.toString());
    }

    @Test
    public void testFindAllMongoDB() {
        final List<User> users = mongoDBRepo.findAll();

        assertTrue(users.size() == 5);
        System.out.println(users.toString());
    }

    @Test
    public void testFindAll() {
        final List<User> users = userRepo.findAll();

        assertTrue(users.size() == 5);
    }

    @Test
    public void testNoFindById() {
        final User user = userRepo.findOne(99L);

        assertNull(user);
    }

    @Test
    public void testCreate() {
        User diana = buildUser("diana.ross@pet.com");
        diana.setPassword("test");
        diana = userRepo.save(diana);
        assertNotNull(diana.getId());
    }

    @Test
    public void testUpdate() {
        final List<User> johns = userRepo.findAllByUserName("johncusack");
        final User john = johns.get(0);
        john.setPassword("newpass");

        userRepo.save(john);

        assertEquals("newpass", john.getPassword());
    }

    @Test
    public void testDelete() {
        final List<User> gigis = userRepo.findAllByUserName("gigipedala");

        final User gigi = gigis.get(0);

        userRepo.delete(gigi.getId().longValue());
    }
}
