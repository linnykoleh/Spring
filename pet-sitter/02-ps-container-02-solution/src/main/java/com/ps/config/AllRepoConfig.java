package com.ps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ps.repos.ResponseRepo;
import com.ps.repos.ReviewRepo;
import com.ps.repos.impl.JdbcResponseRepo;
import com.ps.repos.impl.JdbcReviewRepo;

/**
 * Sample class to depict how @Import
 */
@Configuration
@Import({DataSourceConfig.class, UserRepoDSConfig.class})
public class AllRepoConfig {

    @Bean
    public ReviewRepo reviewRepo(){
        return new JdbcReviewRepo();
    }

    @Bean
    public ResponseRepo responseRepo(){
        return new JdbcResponseRepo();
    }

}
