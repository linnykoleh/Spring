package com.learning.linnyk.schedule.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("com.learning.linnyk.schedule")
public class SpringConfig {

}

