package com.ps;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.config.AllRepoConfig;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.repos.impl.JdbcAbstractRepo;

/**
 * Created by iuliana.cosmina on 3/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AllRepoConfig.class})
public class GenericQualifierTest {


    @Autowired
    JdbcAbstractRepo<Review> reviewRepo;

    @Autowired
    JdbcAbstractRepo<Response> responseRepo;

    @Test
    public void testReviewRepo() {
        assertNotNull(reviewRepo);
    }

    @Test
    public void testResponseRepo() {
        assertNotNull(reviewRepo);
    }
}
