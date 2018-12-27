package com.learning.linnyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DemoConfiguration {

    @Bean
    public List<String> cats() {
        return Arrays.asList("Bengal", "Lion");
    }

}
