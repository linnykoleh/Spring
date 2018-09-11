package com.guitar.db.repository.spring_data;

import com.guitar.db.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ManufacturerDataJPARepository extends JpaRepository<Manufacturer, Long> {

    /* select * from Manufacturer man where man.foundedDate < ? */
    List<Manufacturer> findByFoundedDateBefore(Date date);

    /* select * from Manufacturer man where man.active = 1 */
    List<Manufacturer> findByActiveTrue();

    /* select * from Manufacturer man where man.active = 0 */
    List<Manufacturer> findByActiveFalse();


}
