package com.ps;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

public class ApplicationContextTest {

    @Test
    public void testDataSource1() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-01.xml");
        System.out.println(" >> init done.");
        DataSource dataSource1 = ctx.getBean("dataSource1", DataSource.class);
        assertNotNull(dataSource1);
        System.out.println(" >> usage done.");
        ctx.registerShutdownHook();
    }

    @Test
    public void testDataSource2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-02.xml");

        DataSource dataSource2 = ctx.getBean("dataSource2", DataSource.class);
        assertNotNull(dataSource2);
    }

    @Test
    public void testDataSource3() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-03.xml");

        DataSource dataSource3 = ctx.getBean("dataSource3", DataSource.class);
        assertNotNull(dataSource3);

        Properties dbProp = ctx.getBean("dbProp", Properties.class);
        assertNotNull(dbProp);
    }

}
