package com.learning.linnyk.jb._2_scope_lifecycle;

import java.util.Date;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author LinnykOleh
 */
public class TriangleAware implements ApplicationContextAware, BeanNameAware{

	private String type;
	private String color;
	private ApplicationContext applicationContext;

	public TriangleAware(String type, String color) {
		this.type = type;
		this.color = color;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("Bean name is " + name);
	}

	public void draw() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "Triangle{" +
				"type='" + type + '\'' +
				", color='" + color + '\'' +
				", startupDate=S'" +  new Date(applicationContext.getStartupDate()) + '\'' +
				'}';
	}
}
