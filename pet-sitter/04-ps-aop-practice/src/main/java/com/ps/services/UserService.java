package com.ps.services;

import com.ps.ents.User;

import java.util.Set;

public interface UserService {

    Set<User> findAll();

    int updateUsername(Long id,String username);

    int updatePassword(Long id,String password);

    User findById(Long id);
}
