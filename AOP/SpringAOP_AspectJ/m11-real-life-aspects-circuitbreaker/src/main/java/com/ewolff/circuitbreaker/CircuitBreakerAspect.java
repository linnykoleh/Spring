package com.ewolff.circuitbreaker;

import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Aspect("perthis(com.ewolff.circuitbreaker.CircuitBreakerAspect.circuitBreakerMethods())")
public class CircuitBreakerAspect {

	@Pointcut("execution(@com.ewolff.circuitbreaker.CircuitBreaker * *(..))")
	public void circuitBreakerMethods() {
	}

	private AtomicInteger counter = new AtomicInteger();
	private Throwable throwable;

	@Around("com.ewolff.circuitbreaker.CircuitBreakerAspect.circuitBreakerMethods()")
	public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			if (counter.get() == 0) {
				return joinPoint.proceed();
			}
			if (counter.incrementAndGet() == 10) {
				Object result = joinPoint.proceed();
				counter.set(0);
				return result;
			}
		}
		catch (Throwable throwable) {
			this.throwable = throwable;
			counter.set(1);
		}
		throw this.throwable;
	}

}
