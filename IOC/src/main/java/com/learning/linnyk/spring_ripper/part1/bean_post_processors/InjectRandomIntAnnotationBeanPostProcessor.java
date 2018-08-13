package com.learning.linnyk.spring_ripper.part1.bean_post_processors;

import java.lang.reflect.Field;
import java.util.Random;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import com.learning.linnyk.spring_ripper.part1.annotations.InjectRandomInt;

/**
 * Позволяет настраивать бины до того, как они попадают в контейнер.
 *
 * Между методами postProcessBeforeInitialization и postProcessAfterInitialization вызывается:
 *      - @PostConstruct
 *      - afterPropertiesSet
 * 		- init-method
 *
 * @author LinnykOleh
 */
public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor{

	/**
	 * Вызывается ДО init метода
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		final Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields){
			final InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
			if(annotation != null){
				final int min = annotation.min();
				final int max = annotation.max();
				Random random = new Random();
				int i = random.nextInt(max - min);
				field.setAccessible(true);
				ReflectionUtils.setField(field, bean, i);
			}
		}

		return bean;
	}

	/**
	 * Вызывается ПОСЛЕ init метода
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}
