package com.oreilly.sdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.scheduling.annotation.Async;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findByIds(ID... ids);

    @Async
    List<T> findByIdsAsync(ID...ids);

}
