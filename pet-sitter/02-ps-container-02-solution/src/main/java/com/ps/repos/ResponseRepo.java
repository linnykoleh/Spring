package com.ps.repos;

import java.util.Set;

import com.ps.base.ResponseStatus;
import com.ps.ents.Response;
import com.ps.ents.User;

public interface ResponseRepo extends AbstractRepo<Response>{

    Set<Response> findAllForUser(User user);

    Set<Response> findAllByStatus(ResponseStatus status);
}