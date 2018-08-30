package com.ps.repos;

import com.ps.base.AbstractEntity;

public interface AbstractRepo<T extends AbstractEntity> {

    void save(T entity);

    void delete(T entity);

    void deleteById(Long entityId);

    T findById(Long entityId);
}
