package com.ps.repo;

import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.repos.UserRepo;
import com.ps.ents.User;
import com.ps.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class TestJdbcTemplateUserRepo {

    @Autowired
    @Qualifier("userTemplateRepo")
    private UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
    }

    @Test
    public void testFindById() {
        final User user = userRepo.findById(1L);
        assertEquals("John", user.getUsername());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testNoFindById() {
        final User user = userRepo.findById(111L);
        assertEquals("Darius", user.getUsername());
    }

    @Test
    public void testCount(){
        final int result = userRepo.countUsers();
        assertEquals(4, result);
    }

    @Test
    public void testCreate(){
        int result  = userRepo.createUser(5L, "Diana", "mypass", "diana@opympus.com");
        assertEquals(1, result);
        Set<User> dianas = userRepo.findAllByUserName("Diana", true);
        assertTrue(dianas.size() == 1);
    }

    @Test
    public void testUpdate(){
        int result  = userRepo.updatePassword(1L, "newpass");
        assertEquals(1, result);
    }

    @Test
    public void testDelete(){
        int result  = userRepo.deleteById(4L);
        assertEquals(1, result);
    }

    @Test
    @Sql(statements = {"drop table NEW_P_USER if exists;"})
    public void testCreateTable(){
        int result  = userRepo.createTable("new_p_user");
        // table exists but is empty
        assertEquals(0, result);
    }
}
