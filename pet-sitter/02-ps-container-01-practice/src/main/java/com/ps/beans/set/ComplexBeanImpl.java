package com.ps.beans.set;

import com.ps.beans.ComplexBean;
import com.ps.beans.SimpleBean;

public class ComplexBeanImpl implements ComplexBean {

    private SimpleBean simpleBean;
    private boolean complex;

    public ComplexBeanImpl() {
        System.out.println("[Initialisation ComplexBeanImpl]");
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public boolean isComplex() {
        return complex;
    }

    public void setSimpleBean(SimpleBean simpleBean) {
        System.out.println("[Injecting dependency simpleBean]");
        this.simpleBean = simpleBean;
    }

    public void setComplex(boolean complex) {
        System.out.println("[Injecting dependency complex]");
        this.complex = complex;
    }
}
