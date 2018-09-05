package com.guitar.db.repository.spring_data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Location;

public interface LocationDataJPARepository extends JpaRepository<Location, Long> {

	List<Location> findByStateLike(String state);

}
