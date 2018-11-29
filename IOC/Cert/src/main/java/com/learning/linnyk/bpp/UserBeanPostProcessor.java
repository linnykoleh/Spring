package com.learning.linnyk.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.learning.linnyk.beans.User;

@Component
public class UserBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof User) {
			System.out.println("#4 Before BeanPostProcessor");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof User) {
			System.out.println("#6 After BeanPostProcessor");
		}
		return bean;
	}
}
