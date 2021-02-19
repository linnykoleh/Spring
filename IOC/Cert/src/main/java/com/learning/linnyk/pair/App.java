package com.learning.linnyk.pair;

import com.learning.linnyk.pair.beans.SecondBean;
import com.learning.linnyk.pair.config.PairConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PairConfiguration.class);
        applicationContext.getBean(SecondBean.class).render(); // Key: class Value: 1
    }
}
