package com.example.methodsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.methodsecurity.entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
