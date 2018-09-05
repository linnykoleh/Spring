package com.ps.services;

import com.ps.base.UserType;
import com.ps.ents.User;

public interface UserService {

    User findById(Long id);


    void create(String email, String password, UserType userType);

}
