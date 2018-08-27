package com.ps;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ps.config.RequestRepoConfig;
import com.ps.repos.RequestRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RequestRepoConfig.class})
public class AutowiringTest {

    @Qualifier("requestRepo")
    @Autowired
    RequestRepo reqRepo;

    @Test
    public void testAutowiredReq() {
        assertNotNull(reqRepo);
    }
}
