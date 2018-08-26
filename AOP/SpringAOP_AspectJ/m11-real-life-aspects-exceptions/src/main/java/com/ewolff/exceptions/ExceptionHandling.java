package com.ewolff.exceptions;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionHandling {

	@AfterThrowing(pointcut = "SystemArchitecture.Repository()", throwing = "ex")
	public void logDataAccessException(DataAccessException ex) {
		System.out.println("Problem in Repositories " + ex);
	}

	@AfterThrowing(pointcut = "SystemArchitecture.Repository() || SystemArchitecture.Service()", throwing = "ex")
	public void logRuntimeException(RuntimeException ex) {
		System.out.println("RuntimeException" + ex);
	}

}
