package com.learning.linnyk.jb._2_scope_lifecycle.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author LinnykOleh
 */
@Component
public class TriangleLifecycle implements InitializingBean, DisposableBean{

	private String type;
	private String color;

	public TriangleLifecycle(String type, String color) {
		this.type = type;
		this.color = color;
	}

	/**
	 * Initialisation will be called first
	 */
	@PostConstruct
	public void postConstruct(){
		System.out.println("TriangleLifecycle postConstruct : " + toString());
	}

	/**
	 * Initialisation will be called second
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("TriangleLifecycle afterPropertiesSet : " + toString());
	}

	/**
	 * Initialisation will be called third
	 */
	public void initMethod(){
		System.out.println("TriangleLifecycle initMethod : " + toString());
	}


	/**
	 * Destroying will be called first
	 */
	@PreDestroy
	public void preDestroy(){
		System.out.println("TriangleLifecycle preDestroy : " + toString());
	}

	/**
	 * Destroying will be called second
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("TriangleLifecycle destroy : " + toString());
	}

	/**
	 * Destroying will be called third
	 */
	public void destroyMethod(){
		System.out.println("TriangleLifecycle destroyMethod : " + toString());
	}

	@Override
	public String toString() {
		return "Triangle{" +
				"type='" + type + '\'' +
				", color='" + color + '\'' +
				'}';
	}


}
