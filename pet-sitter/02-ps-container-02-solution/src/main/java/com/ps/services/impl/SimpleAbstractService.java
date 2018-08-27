package com.ps.services.impl;

import com.ps.base.AbstractEntity;
import com.ps.repos.AbstractRepo;
import com.ps.services.AbstractService;


public abstract class SimpleAbstractService<T extends AbstractEntity>  implements AbstractService<T>{

    public void save(T entity) {
        getRepo().save(entity);
    }

    public T findById(Long entityId){
        return getRepo().findById(entityId);
    }

    @Override
    public void delete(T entity) {
        getRepo().delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        getRepo().deleteById(entityId);
    }

    abstract AbstractRepo<T> getRepo();

}
