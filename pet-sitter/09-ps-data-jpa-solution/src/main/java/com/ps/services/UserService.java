package com.ps.services;

import java.util.List;

import com.ps.base.UserType;
import com.ps.ents.User;

public interface UserService {

    User findById(Long id);

    long countUsers();

    void create(String email, String password, UserType userType);

    List<User> findAll();

}
