package com.learning.linnyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("qa")
public class TestConfiguration2 {

    @Bean
    public ProfileBeanHisto profileBeanQA() {
        return new ProfileBeanHisto("qa");
    }
}
