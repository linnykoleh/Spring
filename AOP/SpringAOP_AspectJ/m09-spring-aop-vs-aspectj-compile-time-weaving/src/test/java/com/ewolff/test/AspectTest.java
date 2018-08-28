package com.ewolff.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.ewolff.compiletimeweaving.DemoAspect;
import com.ewolff.demo.DemoClass;

public class AspectTest {

  @Test
  public void adviceIsCalled() {
    assertFalse(DemoAspect.isCalled());
    DemoClass demoClass = new DemoClass();
    demoClass.advicedMethod();
    assertTrue(DemoAspect.isCalled());
  }
}
