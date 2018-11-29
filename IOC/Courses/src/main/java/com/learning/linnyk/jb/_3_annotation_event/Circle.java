package com.learning.linnyk.jb._3_annotation_event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

/**
 * @author LinnykOleh
 */
@Component
public class Circle implements Shape{

	@Autowired
	private Point point;

	@Autowired
	private Triangle triangle;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	private String message;

	@Override
	public void draw(){
		System.out.println(messageSource.getMessage("message.runtime", new Object[] {0, "Hello"}, "Default value", null));

		this.message = messageSource.getMessage("message", null, "Default value", null);
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Circle{" + "\n" +
				"\t" + "message=" + message + "\n" +
				"\t" + "point=" + point + "\n" +
				"\t" + "triangle=" + triangle + "\n" +
				'}';
	}
}
