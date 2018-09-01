package com.ps.beans.ctr;

import com.ps.beans.ComplexBean;
import com.ps.beans.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexBeanImpl implements ComplexBean {

    private Logger logger = LoggerFactory.getLogger(ComplexBeanImpl.class);

    private SimpleBean simpleBean;

    private boolean complex;

    public ComplexBeanImpl(SimpleBean simpleBean) {
        logger.info("[ComplexBeanImpl instantiation]");
        this.simpleBean = simpleBean;
    }

    public ComplexBeanImpl(SimpleBean simpleBean, boolean complex) {
        this.simpleBean = simpleBean;
        this.complex = complex;
    }

    public SimpleBean getSimpleBean() {
        return simpleBean;
    }

    public boolean isComplex() {
        return complex;
    }
}
