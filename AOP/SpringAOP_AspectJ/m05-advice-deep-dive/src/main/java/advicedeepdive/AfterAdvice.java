package advicedeepdive;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAdvice {

  private boolean afterCalled = false;

  public void reset() {
    afterCalled = false;
  }

  public boolean isAfterCalled() {
    return afterCalled;
  }

  @After("execution(* *(..))")
	public void exiting(JoinPoint joinPoint) {
		afterCalled = true;
      System.out.println("exiting " + joinPoint.getSignature());
		for (Object arg : joinPoint.getArgs()) {
            System.out.println("Arg : " + arg);
		}
	}
}
