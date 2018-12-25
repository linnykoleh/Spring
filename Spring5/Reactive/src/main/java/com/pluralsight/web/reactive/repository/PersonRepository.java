package com.pluralsight.web.reactive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.web.reactive.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
