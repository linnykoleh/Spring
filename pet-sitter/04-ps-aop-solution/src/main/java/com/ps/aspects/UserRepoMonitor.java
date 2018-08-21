package com.ps.aspects;

import com.ps.exception.UnexpectedException;
import com.ps.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserRepoMonitor {

    @Before("com.ps.aspects.PointcutContainer.serviceUpdate(service, id, pass)")
    public void beforeServiceUpdate(UserService service, Long id, String pass) {
        System.out.println(" ---> Target object " + service.getClass());

        if (StringUtils.indexOfAny(pass, new String[]{"$", "#", "$", "%"}) != -1) {
            throw new IllegalArgumentException("Text for " + id + " contains weird characters!");
        }
    }

    @AfterReturning(value="execution (* com.ps.services.*Service+.update*(..))", returning = "result")
    public void afterServiceUpdate(JoinPoint joinPoint, int result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(result == 0) {
            System.out.println(" ---> Update method " + className + "." + methodName + " performed as expected.");
        }
    }


    @AfterThrowing(value="execution ( * com.ps.services.*Service+.updateUsername(..))", throwing = "e")
    public void afterBadUpdate(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(e instanceof DuplicateKeyException) {
            System.out.println(" ---> Update method " + className + "." + methodName + " failed. Existing username found.");
        } else {
            throw new UnexpectedException(" Ooops!", e);
        }
    }

    @Before("com.ps.aspects.PointcutContainer.repoUpdate()")
    public void beforeRepoUpdate(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" ---> Method " + className + "." + methodName + " is about to be called");
    }

    @Around("execution(public * com.ps.repos.*.*Repo+.find*(..))")
    public Object monitorFind(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" ---> Intercepting call of: " + methodName);
        long t1 = System.currentTimeMillis();
        try {
            //put a pause here so we can register an execution time
            Thread.sleep(1000L);
            return joinPoint.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            System.out.println(" ---> Execution of " + methodName + " took: " + (t2 - t1) / 1000 + " ms.");
        }
    }

    private static long findByIdCount = 0;

    @After("execution(public * com.ps.repos.*.JdbcUserRepo+.updateUsername(..))")
    public void afterUpdateUsername(JoinPoint joinPoint) {
        ++findByIdCount;
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" ---> Method " + methodName + " was called " + findByIdCount + " times.");
    }

    @Before("com.ps.aspects.PointcutContainer.proxyBubu()")
    public void bubuHappens(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        System.out.println(" ---> BUBU when calling: " + className + "." + methodName);
    }
}
