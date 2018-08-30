package com.ps.services.impl;

import com.ps.ents.Response;
import com.ps.repos.ResponseRepo;
import com.ps.services.ResponseService;

public class SimpleResponseService extends SimpleAbstractService<Response> implements ResponseService {

    private ResponseRepo repo;

    public SimpleResponseService(ResponseRepo responseRepo) {
        this.repo = responseRepo;
    }

    @Override
    public ResponseRepo getRepo() {
        return repo;
    }
}
