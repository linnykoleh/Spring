package com.guitar.db.repository.spring_data;

import com.guitar.db.repository.spring_data.custom.ModelDataCustomJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.guitar.db.model.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ModelDataJPARepository extends JpaRepository<Model, Long>, ModelDataCustomJPARepository {

    /* select * from Manufacturer man left outer join Location loc on man.headquarters_id=loc.id where man.id=?*/
    List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal price1, BigDecimal price2);

    /* select * from Manufacturer man left outer join Location loc on man.headquarters_id=loc.id where man.id=?*/
    List<Model> findByModelTypeNameIn(List<String> names);

    @Query(value = "select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
    List<Model> queryByPriceRangeAndWoodType(@Param("lowest") BigDecimal low,
                                             @Param("highest") BigDecimal high,
                                             @Param("wood") String woodType);

    /*Executes @NamedQuery in Model matches by method name*/
    List<Model> findAllModelsByType(@Param("name") String type);

    /*Executes @NamedQuery in Model matches by @Query name*/
    @Query(name = "Model.findAllModelsByType")
    List<Model> findAllModelsByTypeUsingQuery(@Param("name") String type);

    @Query(value = "select m.name from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
    Page<Model> queryByPriceRangeAndWoodTypePaging(@Param("lowest") BigDecimal low,
                                                   @Param("highest") BigDecimal high,
                                                   @Param("wood") String woodType,
                                                   Pageable page);
}
