package com.learning.linnyk.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.learning.linnyk.schedule.beans.BeanChild;

@Configuration()
@EnableScheduling
@ComponentScan("com.learning.linnyk.schedule")
public class SpringConfig {

}

