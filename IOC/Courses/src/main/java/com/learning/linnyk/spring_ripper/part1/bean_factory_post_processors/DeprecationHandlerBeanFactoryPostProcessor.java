package com.learning.linnyk.spring_ripper.part1.bean_factory_post_processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.learning.linnyk.spring_ripper.part1.annotations.DeprecatedClass;

/**
 * @author LinnykOleh
 */
public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		final String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			final BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
			final String beanClassName = beanDefinition.getBeanClassName();
			try {
				final Class<?> beanClass = Class.forName(beanClassName);
				final DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
				if (annotation != null) {
					beanDefinition.setBeanClassName(annotation.newImpl().getName());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
