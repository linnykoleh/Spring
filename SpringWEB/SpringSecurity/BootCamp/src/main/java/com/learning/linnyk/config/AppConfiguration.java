package com.learning.linnyk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {

//    Disabled due to have another options from Spring Security
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Bean
//    UserDetailsService userDetailsService(){
//        Collection<UserDetails> userDetails = Arrays.asList(
//                new CustomUserDetails("rob", "password", true, "USER"),
//                new CustomUserDetails("bob", "password", true, "USER")
//        );
//        return new CustomUserDetailsService(userDetails);
//    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
