package com.ps.repos.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ps.base.RequestStatus;
import com.ps.ents.Request;
import com.ps.ents.User;
import com.ps.repos.RequestRepo;

@Repository("requestRepo")
public class JdbcRequestRepo  extends JdbcAbstractRepo<Request> implements RequestRepo{

    @Autowired
    public JdbcRequestRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
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
