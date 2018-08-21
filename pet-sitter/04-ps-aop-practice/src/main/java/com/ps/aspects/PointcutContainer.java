package com.ps.aspects;

import com.ps.services.UserService;
import org.aspectj.lang.annotation.Pointcut;

public class PointcutContainer {

    @Pointcut("execution( * com.ps.repos.*.*UserRepo+.update*(Long, String))")
    public void repoUpdate() {
    }

    @Pointcut("execution (* com.ps.services.*Service+.update*(..)) && args(id,pass) && target (service)")
    public void serviceUpdate(UserService service, Long id, String pass) {
    }
}
