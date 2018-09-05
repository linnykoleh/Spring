package com.ps.repos;

import com.ps.ents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepo extends MongoRepository<User, Long> {

    @Query("{'username': { '$regex' : ?0 } }")
    List<User> findAllByUserName(String username);

}