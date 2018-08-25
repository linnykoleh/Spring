package advicedeepdive;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterReturningAdvice {

	private boolean afterReturningCalled = false;

	public void reset() {
		afterReturningCalled = false;
	}

  	boolean isAfterReturningCalled() {
    return afterReturningCalled;
  }

  	@AfterReturning(pointcut = "execution(* *(..))", returning = "string")
	public void logResult(String string) {
		afterReturningCalled = true;
	  	System.out.println("result " + string);
	}

}
