package advicedeepdive;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("result " + string);
	}

}
