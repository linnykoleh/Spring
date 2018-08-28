package com.ps.services.impl;

import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public User findById(Long id) {
        return userRepo.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void htmlAllByNameAll(String name) {
        userRepo.htmlAllByName(name);
    }

    @Override
    public int countUsers() {
        return userRepo.countUsers();
    }

    @Transactional(rollbackFor = MailSendingException.class)
    @Override
    public int updatePassword(Long userId, String newPass) throws MailSendingException {
        final User u = userRepo.findById(userId);
        final String email = u.getEmail();
        final int result = userRepo.updatePassword(userId, newPass);
        sendEmail(email);
        return result;
    }

    private void sendEmail(String email) throws MailSendingException {
        throw new MailSendingException("Confirmation email for password could not be sent. Password was not send.");
    }
}
