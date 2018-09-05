package com.guitar.db.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.ModelType;

public interface ModelTypeDataJPARepository extends JpaRepository<ModelType, Long> {

}
