package com.learning.linnyk.autowire.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainBean {

	private BeanConstruct beanConstruct;

	private BeanSetter beanSetter;
	private BeanMethod beanMethod;
	private BeanMethodReturnValue beanMethodReturnValue;

	@Autowired
	private BeanValue beanValue;

	@Autowired
	public MainBean(BeanConstruct beanConstruct) {
		this.beanConstruct = beanConstruct;
		System.out.println("constructor " + toString());
	}

	@Autowired
	public void autowireBean(BeanMethod beanMethod) {
		this.beanMethod = beanMethod;
		System.out.println("method " + toString());
	}

	@Autowired
	public void setBeanSetter(BeanSetter beanSetter) {
		this.beanSetter = beanSetter;
		System.out.println("setter " + toString());
	}

    @Autowired
    public BeanMethodReturnValue setBeanMethodReturnValue(BeanMethodReturnValue beanMethodReturnValue) {
        this.beanMethodReturnValue = beanMethodReturnValue;
        System.out.println("methodReturnValue " + toString());
        return this.beanMethodReturnValue;
    }

	@Override
	public String toString() {
		return "{" +
				"beanConstruct=" + beanConstruct +
				", beanSetter=" + beanSetter +
				", beanValue=" + beanValue +
				", beanMethod=" + beanMethod +
				", beanMethodReturnValue=" + beanMethodReturnValue +
				'}';
	}
}
