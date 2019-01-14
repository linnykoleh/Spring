package com.learning.linnyk.autowire;

import com.learning.linnyk.autowire.beans.MainBean;
import com.learning.linnyk.autowire.config.AutowireConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		final AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext(AutowireConfiguration.class);
		annotationConfigApplicationContext.getBean(MainBean.class);

        // constructor {beanConstruct=BeanConstruct, beanSetter=null, beanValue=null, beanMethod=null, beanMethodReturnValue=null}
        // method {beanConstruct=BeanConstruct, beanSetter=null, beanValue=BeanValue, beanMethod=BeanMethod, beanMethodReturnValue=null}
        // setter {beanConstruct=BeanConstruct, beanSetter=BeanSetter, beanValue=BeanValue, beanMethod=BeanMethod, beanMethodReturnValue=null}
        // methodReturnValue {beanConstruct=BeanConstruct, beanSetter=BeanSetter, beanValue=BeanValue, beanMethod=BeanMethod, beanMethodReturnValue=BeanMethodReturnValue}

		// #1 autowire by constructor
		// #2 autowire by type 	     - value inject using reflection
		// #3 autowire by method
		// #4 autowire by setter
	}
}
