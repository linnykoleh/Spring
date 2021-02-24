package com.ps.services.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceInterviewImpl {

    private final UserRepo userRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User findById(Long id) {
        log.debug(">>> Preparing to execute SERVICE.findById");
        User user = userRepo.findById(id);
        log.debug(">>> Done executing REPO.findById");
        int users = countUsers();
        log.debug(">>> Users {0}");
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int countUsers() {
        return userRepo.countUsers();
    }

}
