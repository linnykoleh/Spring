package com.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.ps.repos.impl", "com.ps.services" ,"com.ps.aspects"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
