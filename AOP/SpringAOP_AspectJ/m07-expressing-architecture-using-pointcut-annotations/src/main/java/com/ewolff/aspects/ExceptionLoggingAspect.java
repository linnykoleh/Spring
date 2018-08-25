package com.ewolff.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionLoggingAspect extends CallTracker {

	@AfterThrowing(pointcut = "SystemArchitecture.Repository() || SystemArchitecture.Service()", throwing = "ex")
	public void logException(Exception ex) {
		trackCall();
		System.out.println("Exception " + ex);
	}

}
