package com.ewolff.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect extends CallTracker {

	@Around("SystemArchitecture.Repository() || SystemArchitecture.Service()")
	public void trace(ProceedingJoinPoint proceedingJP) throws Throwable {
		final String methodInformation = proceedingJP.getStaticPart().getSignature().toString();
		System.out.println("Entering " + methodInformation);
		trackCall();
		try {
			proceedingJP.proceed();
		}
		catch (Throwable ex) {
			System.out.println("Exception in " + methodInformation + ex);
			throw ex;
		}
		finally {
			System.out.println("Exiting " + methodInformation);
		}
	}

}
