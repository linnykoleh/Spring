package com.ps.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ps.ents.User;

public interface MongoDBRepo extends MongoRepository<User, Long> {
}
