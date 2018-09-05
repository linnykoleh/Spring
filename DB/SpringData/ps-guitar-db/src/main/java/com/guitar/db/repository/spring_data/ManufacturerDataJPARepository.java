package com.guitar.db.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Manufacturer;

public interface ManufacturerDataJPARepository extends JpaRepository<Manufacturer, Long> {

}
