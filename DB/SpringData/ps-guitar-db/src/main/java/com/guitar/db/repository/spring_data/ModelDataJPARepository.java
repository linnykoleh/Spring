package com.guitar.db.repository.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Model;

import java.math.BigDecimal;
import java.util.List;

public interface ModelDataJPARepository extends JpaRepository<Model, Long> {

    /* select * from Manufacturer man left outer join Location loc on man.headquarters_id=loc.id where man.id=?*/
    List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal price1, BigDecimal price2);

    /* select * from Manufacturer man left outer join Location loc on man.headquarters_id=loc.id where man.id=?*/
    List<Model> findByModelTypeNameIn(List<String> names);

}
