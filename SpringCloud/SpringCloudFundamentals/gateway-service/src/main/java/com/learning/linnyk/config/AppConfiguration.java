package com.learning.linnyk.config;

import com.learning.linnyk.filter.AddRequestHeaderFilter;
import com.netflix.zuul.ZuulFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ZuulFilter zuulFilter(){
        return new AddRequestHeaderFilter();
    }
}
