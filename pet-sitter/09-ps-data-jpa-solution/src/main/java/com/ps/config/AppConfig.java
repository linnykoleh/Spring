package com.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ps.repos", "com.ps.services.impl", "com.ps.init", "com.ps.config.db"})
public class AppConfig {

}
