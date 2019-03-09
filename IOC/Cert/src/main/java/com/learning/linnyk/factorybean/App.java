package com.learning.linnyk.factorybean;

import com.learning.linnyk.factorybean.beans.MainBean;
import com.learning.linnyk.factorybean.config.FactoryBeanConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        final ApplicationContext context =
                new AnnotationConfigApplicationContext(FactoryBeanConfiguration.class);

        MainBean bean = context.getBean(MainBean.class);
        System.out.println(bean.hashCode()); //2076287037

        MainBean bean1 = context.getBean(MainBean.class);
        System.out.println(bean1.hashCode()); //1890627974
    }
}
