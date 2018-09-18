package com.oreilly.sdata.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class MongoDBConfig {

	private static final String DB_NAME = "sandbox";
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
