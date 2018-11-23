package advicedeepdive;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AroundAdvice {

	private boolean called;

	public void reset() {
		called = false;
	}

	@Around("execution(* *(..))")
	public Object trace(ProceedingJoinPoint proceedingJP) throws Throwable {
		String methodInformation =
				proceedingJP.getStaticPart().getSignature().toString();
		System.out.println("Entering " + methodInformation);
		called = true;
		try {
			return proceedingJP.proceed();
		}
		catch (Throwable ex) {
			System.out.println("Exception in " + methodInformation + ex);
			throw ex;
		}
		finally {
			System.out.println("Exiting " + methodInformation);
		}
	}

	boolean isCalled() {
		return called;
	}

}
