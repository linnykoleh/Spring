package advicedeepdive;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
      log.info("exiting " + joinPoint.getSignature());
		for (Object arg : joinPoint.getArgs()) {
            log.info("Arg : " + arg);
		}
	}
}
