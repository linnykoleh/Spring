import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {

	@Pointcut("execution(* com.ewolff..repository.*Repository.*(..))")
	public void Repository() {
	}

	@Pointcut("execution(* com.ewolff..service.*.*(..))")
	public void Service() {
	}

}
