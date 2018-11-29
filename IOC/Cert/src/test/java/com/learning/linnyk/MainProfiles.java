package com.learning.linnyk;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainProfiles {

    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(TestConfiguration1.class);
        context.refresh();

        final ProfileBeanLive bean = context.getBean(ProfileBeanLive.class);
        System.out.println(bean); // ProfileBeanLive{profileValue='dev'}
    }
}
