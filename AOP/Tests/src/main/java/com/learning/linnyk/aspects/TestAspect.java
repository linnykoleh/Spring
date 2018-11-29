package com.learning.linnyk.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

	@Before("execution(* com.learning.linnyk.services.MyClass.*(..)) && execution(* com.learning.linnyk.services.MyOtherClass.*(..))")
	public void testExecution1() {
		System.out.println("testExecution1");
	}

	@Before("execution(* com.learning.linnyk.services.MyClass.*(..)) || execution(* com.learning.linnyk.services.MyOtherClass.*(..))")
	public void testExecution2() {
		System.out.println("testExecution2");
	}

	@Before("execution(* com.learning.linnyk.services.MyClass.*(..))")
	public void testExecution3() {
		System.out.println("testExecution3");
	}

}
