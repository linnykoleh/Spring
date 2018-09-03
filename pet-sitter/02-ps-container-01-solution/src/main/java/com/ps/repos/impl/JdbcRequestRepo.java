package com.ps.repos.impl;

import com.ps.base.RequestStatus;
import com.ps.ents.Request;
import com.ps.ents.User;
import com.ps.repos.RequestRepo;

import javax.sql.DataSource;
import java.util.Set;

public class JdbcRequestRepo  extends JdbcAbstractRepo<Request> implements RequestRepo{

    public JdbcRequestRepo(){
    }

    public JdbcRequestRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Set<Request> findAllForUser(User user) {
        return null;
    }

    @Override
    public Set<Request> findByStatus(RequestStatus status) {
        return null;
    }
}
