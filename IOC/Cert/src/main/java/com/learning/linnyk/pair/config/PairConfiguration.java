package com.learning.linnyk.pair.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.learning.linnyk.pair")
@PropertySource("classpath:pair/pair.properties")
public class PairConfiguration {
}
