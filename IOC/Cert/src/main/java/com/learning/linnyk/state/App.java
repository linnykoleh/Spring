package com.learning.linnyk.state;

import com.learning.linnyk.state.beans.StatefulBean;
import com.learning.linnyk.state.config.StateConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    private static AnnotationConfigApplicationContext context;

    public static void main(String[] args) throws InterruptedException {

        context = new AnnotationConfigApplicationContext(StateConfiguration.class);

        final StatefulBean statefulBean = context.getBean(StatefulBean.class);
        statefulBean.addToState(Thread.currentThread().getName());

        doInThread();
        doInThread();
        doInThread();

        Thread.sleep(100);
        final StatefulBean statefulBeanFinal = context.getBean(StatefulBean.class);
        final List<String> sharedState = statefulBeanFinal.getSharedState();

        System.out.println(sharedState); // []
    }

    private static void doInThread() throws InterruptedException {
        Thread.sleep(100);
        new Thread(() -> {
            final StatefulBean statefulBean1 = context.getBean(StatefulBean.class);
            statefulBean1.addToState(Thread.currentThread().getName());
        }).start();
    }
}
