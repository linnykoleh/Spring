package com.learning.linnyk.spring_ripper.part1.bean_post_processors;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.learning.linnyk.spring_ripper.part1.ProfilingController;
import com.learning.linnyk.spring_ripper.part1.annotations.Profiling;

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
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

	private Map<String, Class> map = new HashMap<>();
	private ProfilingController controller = new ProfilingController();

	public ProfilingHandlerBeanPostProcessor() throws Exception {
		final MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
		platformMBeanServer.registerMBean(controller, new ObjectName("profiling", "name", "controller"));
	}

	/**
	 * Вызывается ДО init метода
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		final Class<?> aClass = bean.getClass();
		if(aClass.isAnnotationPresent(Profiling.class)){
			map.put(beanName, aClass);
		}
		return bean;
	}

	/**
	 * Вызывается ПОСЛЕ init метода
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		final Class beanClass = map.get(beanName);
		if(beanClass != null){
			return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
				if(controller.isEnabled()) {
					System.out.println("Profiling started");
					final long before = System.nanoTime();

					final Object invoke = method.invoke(bean, args);

					final long after = System.nanoTime();
					System.out.println(after - before);
					System.out.println("Profiling done");
					return invoke;
				}else {
					return method.invoke(bean, args);
				}
			});
		}
		return bean;
	}
}
