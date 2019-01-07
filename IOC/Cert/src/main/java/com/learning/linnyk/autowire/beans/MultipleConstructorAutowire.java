package com.learning.linnyk.autowire.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MultipleConstructorAutowire {

    private BeanConstruct beanConstruct;
    private BeanMethod beanMethod;

    @Autowired
    public MultipleConstructorAutowire(BeanConstruct beanConstruct) {
        this.beanConstruct = beanConstruct;
    }

    @Autowired(required = false)
    public MultipleConstructorAutowire(BeanMethod beanMethod) {
        this.beanMethod = beanMethod;
    }

    @Autowired(required = false)
    public MultipleConstructorAutowire(BeanConstruct beanConstruct, BeanMethod beanMethod) {
        this.beanConstruct = beanConstruct;
        this.beanMethod = beanMethod;
    }

    @Override
    public String toString() {
        return "MultipleConstructorAutowire{" +
                "beanConstruct=" + beanConstruct +
                ", beanMethod=" + beanMethod +
                '}';
    }
}
