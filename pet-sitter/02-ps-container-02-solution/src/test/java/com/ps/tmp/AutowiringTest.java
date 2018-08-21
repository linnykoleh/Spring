package com.ps.tmp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AutowiringTest {

    @Autowired
    QuizBean quizBean1;

    @Autowired
    QuizBean quizBean2;

    @Test
    public void testAutowireReq() {
        assertNotEquals(quizBean1, quizBean2);
    }
}
