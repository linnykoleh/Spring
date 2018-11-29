package com.learning.linnyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestConfiguration1 {

    @Bean
    @Profile("dev")
    public ProfileBeanLive profileBeanLiveDev() {
        return new ProfileBeanLive("dev");
    }

    @Bean
    @Profile("prod")
    public ProfileBeanLive profileBeanLiveProd() {
        return new ProfileBeanLive("prod");
    }
}
