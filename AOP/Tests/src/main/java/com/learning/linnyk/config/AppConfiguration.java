package com.learning.linnyk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.learning.linnyk")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
