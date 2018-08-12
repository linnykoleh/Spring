package com.ps.beans.ctr;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class CIBeansTest {

    @Test
    public void testConfig() {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-01.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/ctr/sample-config-02.xml");

        System.out.println(" --- All beans in context --- ");
        for(String beanName :  ctx.getBeanDefinitionNames()) {
            System.out.println("Bean " + beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
        }

        ComplexBeanImpl complexBean0 = (ComplexBeanImpl) ctx.getBean("complexBean0");
        assertNotNull(complexBean0.getSimpleBean());

        ComplexBeanImpl complexBean1 = (ComplexBeanImpl) ctx.getBean("complexBean1");
        assertNotNull(complexBean1.getSimpleBean());
        assertTrue(complexBean1.isComplex());

        ComplexBean2Impl complexBean2 = (ComplexBean2Impl) ctx.getBean("complexBean2");
        assertNotNull(complexBean2.getSimpleBean1());
        assertNotNull(complexBean2.getSimpleBean2());
    }
}
