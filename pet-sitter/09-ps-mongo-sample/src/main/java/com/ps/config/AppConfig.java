package com.ps.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.ps.repos")
@ComponentScan(basePackages = { "com.ps.init"})
public class AppConfig {

    private static final String DB_NAME = "test";
    private static final String MONGO_HOST = "127.0.0.1";
    private static final int MONGO_PORT = 27017;

    @Bean
    public MongoDbFactory mongoDb() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(MONGO_HOST, MONGO_PORT), DB_NAME);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws  Exception {
        return new MongoTemplate(mongoDb());
    }
}
