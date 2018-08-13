package com.learning.linnyk.jb._2_scope_lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author LinnykOleh
 */
public class DisplayNameBeanPostProcessor implements BeanPostProcessor {

	/*
	  BeanPostProcessor tells spring there some processing that spring have to do
	  after initialising a bean.
	  Spring execute these method for each bean.
	  BeanPostProcessor used to extend spring's functionality.
	  */

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Called postProcessBeforeInitialization " + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("Called postProcessAfterInitialization " + beanName);
		return bean;
	}
}
