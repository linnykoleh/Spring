package com.ps.repos.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ps.base.ResponseStatus;
import com.ps.ents.Response;
import com.ps.ents.User;
import com.ps.repos.ResponseRepo;

@Repository("responseRepo")
public class JdbcResponseRepo extends JdbcAbstractRepo<Response>  implements ResponseRepo{

    @Autowired
    public JdbcResponseRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<Response> findAllForUser(User user) {
        return null;
    }

    @Override
    public Set<Response> findAllByStatus(ResponseStatus status) {
        return null;
    }
}
