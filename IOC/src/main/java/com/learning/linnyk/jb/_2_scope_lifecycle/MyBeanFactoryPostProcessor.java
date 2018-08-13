package com.learning.linnyk.jb._2_scope_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author LinnykOleh
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	/*
	 Modify the application context's internal bean factory after its standard
	 initialization. All bean definitions will have been loaded, but no beans
	 will have been instantiated yet. This allows for overriding or adding
	 properties even to eager-initializing beans.

	 https://habrahabr.ru/post/222579/
	*/

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor::postProcessBeanFactory");
		final String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for(String beanDefinitionName: beanDefinitionNames) {
			final BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
			System.out.println(beanDefinition);
		}
		System.out.println("===========================================");
		System.out.println("===========================================");
		System.out.println("===========================================");
		System.out.println("===========================================");
		System.out.println("===========================================");
	}
}
