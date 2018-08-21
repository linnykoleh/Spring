package com.ps;

import com.ps.sample.TestBeanOne;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class MixedConfigTest {

    private ApplicationContext ctx;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-05.xml");
        assertNotNull(ctx);
    }

    /**
     * Bootstrap Configuration class using context class
     */
    @Test
    public void testOne() {
        TestBeanOne tb =  ctx.getBean(TestBeanOne.class);
        assertNotNull(tb);
    }
}
