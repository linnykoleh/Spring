package com.ps.repos.impl;

import com.ps.base.ResponseStatus;
import com.ps.ents.Response;
import com.ps.ents.User;
import com.ps.repos.ResponseRepo;

import javax.sql.DataSource;
import java.util.Set;

public class JdbcResponseRepo extends JdbcAbstractRepo<Response>  implements ResponseRepo{

    public JdbcResponseRepo(){
    }

    public JdbcResponseRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Set<Response> findAllForUser(User user) {
        return null;
    }

    @Override
    public Set<Response> findAllByStatus(ResponseStatus status) {
        return null;
    }
}
