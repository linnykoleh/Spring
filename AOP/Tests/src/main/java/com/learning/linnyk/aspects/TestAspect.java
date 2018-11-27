package com.learning.linnyk.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TestAspect {

	@Before("execution(* com.learning.linnyk.services.MyClass.*(..)) && execution(* com.learning.linnyk.services.MyOtherClass.*(..))")
	public void testExecution1() {
		log.info("testExecution1");
	}

	@Before("execution(* com.learning.linnyk.services.MyClass.*(..)) || execution(* com.learning.linnyk.services.MyOtherClass.*(..))")
	public void testExecution2() {
		log.info("testExecution2");
	}

	@Before("execution(* com.learning.linnyk.services.MyClass.*(..))")
	public void testExecution3() {
		log.info("testExecution3");
	}


}
