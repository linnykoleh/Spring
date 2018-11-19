package advicedeepdive;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AfterThrowingAdvice {

	private boolean afterThrowingCalled = false;

	public void reset() {
		afterThrowingCalled = false;
	}

	boolean isAfterThrowingCalled() {
		return afterThrowingCalled;
	}

	@AfterThrowing(pointcut = "execution(void throwsRuntimeException())", throwing = "ex")
	public void logException(RuntimeException ex) {
		afterThrowingCalled = true;
		log.info("Exception " + ex);
	}

}
