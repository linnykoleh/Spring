package com.learning.linnyk.spring_ripper.part2.bean_post_processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.learning.linnyk.spring_ripper.part2.scope.PeriodicalScopeConfigurer;

/**
 * @author LinnykOleh
 */
public class CustomScopeRegistryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		beanFactory.registerScope("periodical", new PeriodicalScopeConfigurer());
	}
}
