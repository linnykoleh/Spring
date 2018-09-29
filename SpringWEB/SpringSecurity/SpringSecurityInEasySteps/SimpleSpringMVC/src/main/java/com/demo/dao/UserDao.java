package com.demo.dao;

import java.util.List;

import com.demo.to.User;

public interface UserDao {

    String save(User u);

    void update(User u);

    String authenticateUser(User user);

    List<User> list();
}
