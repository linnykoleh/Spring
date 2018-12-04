package com.learning.linnyk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration4 {

	@Bean
	public ProfileBeanLive profileBeanLiveDev() {
		return new ProfileBeanLive("live without profile");
	}

}
