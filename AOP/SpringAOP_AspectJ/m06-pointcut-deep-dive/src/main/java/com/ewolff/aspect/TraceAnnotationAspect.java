package com.ewolff.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TraceAnnotationAspect {
	
	private int called=0;

	@Around("execution(@annotation.Trace * *(..))")
	public void trace(ProceedingJoinPoint proceedingJP ) throws Throwable {
		String methodInformation = 
				proceedingJP.getStaticPart().getSignature().getName();
		System.out.println("Entering "+methodInformation);
		called++;
		try {
			proceedingJP.proceed();
		} catch (Throwable ex) {
			System.out.println("Exception in " + methodInformation + ex);
			throw ex;
		} finally {
			System.out.println("Exiting "+methodInformation);
		}
	}

	public void resetCalled() {
		called=0;
	}
	
	public int getCalled() {
		return called;
	}

}