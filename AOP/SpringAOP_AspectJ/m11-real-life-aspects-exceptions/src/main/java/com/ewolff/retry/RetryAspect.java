package com.ewolff.retry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RetryAspect {

	@Around("execution(* com.ewolff.retry..*Service.*(..))")
	public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			return joinPoint.proceed();
		}
		catch (Throwable e) {
			return joinPoint.proceed();
		}
	}

}
