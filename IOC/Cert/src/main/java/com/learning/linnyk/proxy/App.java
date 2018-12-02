package com.learning.linnyk.proxy;

import com.learning.linnyk.proxy.config.AppProxyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(AppProxyConfiguration.class);
    }
}
