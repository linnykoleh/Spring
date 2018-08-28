package com.ps.repos;

import java.util.Set;

import com.ps.base.RequestStatus;
import com.ps.ents.Request;
import com.ps.ents.User;

public interface RequestRepo extends AbstractRepo<Request>{

    Set<Request> findAllForUser(User user);

    Set<Request> findByStatus(RequestStatus status);
}