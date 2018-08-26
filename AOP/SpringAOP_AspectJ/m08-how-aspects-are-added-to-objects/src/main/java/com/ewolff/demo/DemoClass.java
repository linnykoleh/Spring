package com.ewolff.demo;

import org.springframework.stereotype.Component;

@Component
public class DemoClass {

  
  public void advicedMethod() {

  }

  
  public void callsTheAdvicedMethod() {
    advicedMethod();
  }

}
