package com.ps.beans.set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SIBeansTest {

    @Test
    public void testConfig() {
        // ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/set/sample-config-01.xml");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/set/sample-config-02.xml");

        System.out.println(" --- All beans in context --- ");
        for(String beanName :  ctx.getBeanDefinitionNames()) {
            System.out.println("Bean " + beanName + " of type " + ctx.getBean(beanName).getClass().getSimpleName());
        }

        final ComplexBeanImpl complexBean0 = ctx.getBean("complexBean0", ComplexBeanImpl.class);
        Assert.assertNotNull(complexBean0.getSimpleBean());
        Assert.assertFalse(complexBean0.isComplex());

        final ComplexBeanImpl complexBean1 = ctx.getBean("complexBean1", ComplexBeanImpl.class);
        Assert.assertNotNull(complexBean1.getSimpleBean());
        Assert.assertTrue(complexBean1.isComplex());

        final ComplexBean2Impl complexBean2 = ctx.getBean("complexBean2", ComplexBean2Impl.class);
        Assert.assertNotNull(complexBean2.getSimpleBean());
        Assert.assertTrue(complexBean2.isComplex());
    }
}
