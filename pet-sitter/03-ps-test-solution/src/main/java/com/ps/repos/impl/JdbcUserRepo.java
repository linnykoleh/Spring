package com.ps.repos.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ps.ents.User;
import com.ps.repos.UserRepo;

@Repository("userRepo")
public class JdbcUserRepo extends JdbcAbstractRepo<User> implements UserRepo {

    @Autowired
    public JdbcUserRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<User> findAllByUserName(String username, boolean exactMatch) {
        return null;
    }

    @Override
    public Set<User> findByRating(double startRating, double endRating) {
        return null;
    }
}
