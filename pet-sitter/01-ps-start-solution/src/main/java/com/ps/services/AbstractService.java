package com.ps.services;


public interface AbstractService<T> {

    void save(T entity);

    T findById(Long entityId);

    void delete(T entity);

    void deleteById(Long entityId);
}
