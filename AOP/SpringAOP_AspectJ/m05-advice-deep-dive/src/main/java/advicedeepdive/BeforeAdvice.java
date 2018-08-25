package advicedeepdive;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeAdvice {

  private boolean beforeCalled = false;

  public void reset() {
    beforeCalled = false;
  }

  boolean isBeforeCalled() {
    return beforeCalled;
  }

  @Before("execution(void doSomething())")
  public void entering(JoinPoint joinPoint) {
    beforeCalled = true;
    System.out.println("entering " + joinPoint.getStaticPart().getSignature().toString());
  }

}
