package com.ps.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleBeanImpl implements SimpleBean {

    private Logger logger = LoggerFactory.getLogger(SimpleBean.class);

    public SimpleBeanImpl() {
        logger.info("[SimpleBeanImpl instantiation]");
    }

    @Override
    public String toString() {
        return "SimpleBeanImpl{ code: " + hashCode() + "}";
    }
}
