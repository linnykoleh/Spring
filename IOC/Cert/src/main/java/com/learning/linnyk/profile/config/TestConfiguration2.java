package com.learning.linnyk.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.learning.linnyk.profile.beans.ProfileBeanHisto;

@Configuration
@Profile("qa")
public class TestConfiguration2 {

    @Bean
    public ProfileBeanHisto profileBeanQA() {
        return new ProfileBeanHisto("qa");
    }
}
