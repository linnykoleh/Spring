package com.learning.linnyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("stage")
public class TestConfiguration3 {

    @Bean
    public ProfileBeanHisto profileBeanStage() {
        return new ProfileBeanHisto("stage");
    }
}
