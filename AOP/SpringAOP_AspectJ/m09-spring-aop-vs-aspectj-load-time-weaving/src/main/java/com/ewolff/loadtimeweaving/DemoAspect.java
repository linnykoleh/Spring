package com.ewolff.loadtimeweaving;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DemoAspect {

	@Before("execution(void advicedMethod())")
	public void logException() {
		System.out.println("Aspect called!");
	}

}
