package com.learning.linnyk.spring_ripper.part1.context_listeners;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.learning.linnyk.spring_ripper.part1.annotations.PostProxy;

/**
 * @author LinnykOleh
 */
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ConfigurableListableBeanFactory factory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		final ApplicationContext context = event.getApplicationContext();
		final String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for(String beanName : beanDefinitionNames){
			final BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
			final String originalClassName = beanDefinition.getBeanClassName();
			try {
				final Class<?> originalClass = Class.forName(originalClassName);
				final Method[] methods = originalClass.getMethods();
				for(Method method : methods){
					if(method.isAnnotationPresent(PostProxy.class)){
						final Object bean = context.getBean(beanName);
						final Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
						currentMethod.invoke(bean);
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
