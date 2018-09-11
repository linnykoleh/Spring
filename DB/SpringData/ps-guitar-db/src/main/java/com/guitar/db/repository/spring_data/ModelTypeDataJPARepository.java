package com.guitar.db.repository.spring_data;

import com.guitar.db.model.ModelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelTypeDataJPARepository extends JpaRepository<ModelType, Long> {

    /* select * from ModelType mt where mt.name is null*/
    List<ModelType> findByNameIsNull();

}
