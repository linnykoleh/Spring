package com.ps.repos;

import com.ps.base.AbstractEntity;

public interface AbstractRepo<T extends AbstractEntity> {

    void save(T entity);

    void delete(T entity) throws NotFoundException;

    void deleteById(Long entityId) throws NotFoundException;

    T findById(Long entityId);
}
