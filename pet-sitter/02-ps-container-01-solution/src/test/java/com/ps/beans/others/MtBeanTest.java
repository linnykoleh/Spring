package com.ps.beans.others;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MtBeanTest {

    @Test
    public void testConfig() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/others/sample-config-01.xml");

        MultipleTypesBean mtBean = (MultipleTypesBean) ctx.getBean("mtBean");
        assertNotNull(mtBean);

        //expected no of beans in the context
        int beanCount1 = ctx.getBeanDefinitionNames().length;

        ApplicationContext ctx2 = new ClassPathXmlApplicationContext("classpath:spring/others/*-cfg.xml");

        int beanCount2 = ctx2.getBeanDefinitionNames().length;

        assertEquals(beanCount1, beanCount2);

    }
}
