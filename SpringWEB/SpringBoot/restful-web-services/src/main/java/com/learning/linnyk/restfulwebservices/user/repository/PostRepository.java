package com.learning.linnyk.restfulwebservices.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.linnyk.restfulwebservices.user.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
