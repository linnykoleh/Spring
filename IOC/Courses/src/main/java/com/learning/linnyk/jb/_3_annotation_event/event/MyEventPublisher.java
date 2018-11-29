package com.learning.linnyk.jb._3_annotation_event.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author LinnykOleh
 */
@Component
public class MyEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher eventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}

	public void publish(){
		eventPublisher.publishEvent(new DrawEvent(this));
	}
}
