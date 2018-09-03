package com.ps.repos;

import java.util.Set;

import com.ps.ents.User;
public interface UserRepo  extends AbstractRepo<User> {

    Set<User> findAllByUserName(String username, boolean exactMatch);

    Set<User> findByRating(double startRating, double endRating);
}