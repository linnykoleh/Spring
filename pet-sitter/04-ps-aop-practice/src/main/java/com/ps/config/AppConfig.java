package com.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ps.repos.impl", "com.ps.services" ,"com.ps.aspects"})
//TODO 20. Enable automatic @Aspect detection
public class AppConfig {
}
