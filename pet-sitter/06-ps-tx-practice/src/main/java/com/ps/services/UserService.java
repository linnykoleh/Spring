package com.ps.services;

import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;

public interface UserService {

    User findById(Long id);

    void htmlAllByNameAll(String name);

    int countUsers();

    int updatePassword(Long userId, String newPass) throws MailSendingException;
}
