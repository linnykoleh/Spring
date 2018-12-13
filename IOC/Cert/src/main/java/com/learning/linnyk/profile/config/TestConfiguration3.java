package com.learning.linnyk.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.learning.linnyk.profile.beans.ProfileBeanHisto;

@Configuration
@Profile("stage")
public class TestConfiguration3 {

    @Bean
    public ProfileBeanHisto profileBeanStage() {
        return new ProfileBeanHisto("stage");
    }
}
