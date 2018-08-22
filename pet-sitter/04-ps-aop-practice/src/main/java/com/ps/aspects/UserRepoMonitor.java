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

    @Before("execution (* com.ps.services.UserService.update*(..)) && args(id,pass) && target (service)")
    public void beforeServiceUpdate(UserService service, Long id, String pass) {
        System.out.println(" ---> Target object " + service.getClass());

        if (StringUtils.indexOfAny(pass, new String[]{"$", "#", "$", "%"}) != -1) {
            throw new IllegalArgumentException("Text for " + id + " contains weird characters!");
        }
    }

    @AfterReturning(value="execution (* com.ps.services.UserService.update*(..))", returning = "result")
    public void afterServiceUpdate(JoinPoint joinPoint, int result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        if(result == 0) {
            System.out.println(" ---> Update method " + className + "." + methodName + " performed as expected.");
        }
    }

    @AfterThrowing(value="execution ( * com.ps.services.UserService.updateUsername(..))", throwing = "e")
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
            return joinPoint.proceed();
        } finally {
            long t2 = System.currentTimeMillis();
            System.out.println(" ---> Execution of " + methodName + " took: " + (t2 - t1) / 1000 + " ms.");
        }
    }

    private static long findByIdCount = 0;

    @After("execution(public * com.ps.repos.*.JdbcTemplateUserRepo+.updateUsername(..))")
    public void afterFindById(JoinPoint joinPoint) {
        ++findByIdCount;
        String methodName = joinPoint.getSignature().getName();
        System.out.println(" ---> Method " + methodName + " was called " + findByIdCount + " times.");
    }
}
