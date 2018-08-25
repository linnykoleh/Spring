package com.ewolff.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class PerformanceAspect extends CallTracker {

	@Around("SystemArchitecture.Repository()")
	public void trace(ProceedingJoinPoint proceedingJP) throws Throwable {
		String methodInformation = proceedingJP.getStaticPart().getSignature().toString();
		final StopWatch stopWatch = new StopWatch(methodInformation);
		stopWatch.start();
		trackCall();
		try {
			proceedingJP.proceed();
		} finally {
			stopWatch.stop();
			System.out.println(stopWatch.shortSummary());
		}
	}

}
