package com.ps.beans.others;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class MtBeanTest {

    @Test
    public void testConfig() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "classpath:spring/others/sample-config-01.xml",
                "classpath:spring/others/multiple-types-bean-config.xml");

        MultipleTypesBean mtBean = (MultipleTypesBean) ctx.getBean("mtBean");
        assertNotNull(mtBean);
    }
}
