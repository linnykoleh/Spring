package com.guitar.db.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Model;

public interface ModelDataJPARepository extends JpaRepository<Model, Long> {

}
