package com.learning.linnyk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration1.class, TestConfiguration2.class, TestConfiguration3.class})
@ActiveProfiles({"dev", "qa"})
public class ProfilesTest {

    @Autowired
    private ProfileBeanLive profileBeanLive;

    @Autowired
    private ProfileBeanHisto profileBeanHisto;


    @Test
    public void test() {
        System.out.println(profileBeanLive);
        System.out.println(profileBeanHisto);
        assertEquals("dev", profileBeanLive.getProfileValue());
        assertEquals("qa", profileBeanHisto.getProfileValue());
    }
}
