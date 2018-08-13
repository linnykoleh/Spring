package com.learning.linnyk.jb._3_annotation_event.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author LinnykOleh
 */
public class DrawingEventApp {

	public static void main(String[] args) {

		final ApplicationContext beanFactory = new ClassPathXmlApplicationContext("jb/_3_annotation_event/spring.xml");

		final MyEventPublisher publisherAware = (MyEventPublisher)beanFactory.getBean("myEventPublisher");
		publisherAware.publish();

	}
}
