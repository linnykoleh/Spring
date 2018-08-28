package com.ewolff.compiletimeweaving;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DemoAspect {

  static boolean called=false;
  
	@Before("execution(void advicedMethod())")
	public void logException() {
		System.out.println("Aspect called!");
		called=true;
	}

  public static boolean isCalled() {
    return called;
  }

  public static void setCalled(boolean called) {
    DemoAspect.called = called;
  }

}
