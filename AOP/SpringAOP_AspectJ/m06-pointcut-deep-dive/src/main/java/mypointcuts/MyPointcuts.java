package mypointcuts;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcuts {

	@Pointcut("bean(*Service)")
	public void beanNamePointcut() {
	}

}
