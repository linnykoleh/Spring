package com.learning.linnyk.factorybean.beans.factory;

import com.learning.linnyk.factorybean.beans.MainBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MainBeanFactory implements FactoryBean<MainBean> {

    @Override
    public MainBean getObject() {
        return new MainBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MainBean.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
