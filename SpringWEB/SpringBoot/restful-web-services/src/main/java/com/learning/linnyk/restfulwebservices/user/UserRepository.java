package com.learning.linnyk.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.linnyk.restfulwebservices.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
