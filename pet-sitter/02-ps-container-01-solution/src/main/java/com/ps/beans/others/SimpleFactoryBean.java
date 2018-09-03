package com.ps.beans.others;

import com.ps.beans.SimpleBean;
import com.ps.beans.SimpleBeanImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleFactoryBean {
    private static Logger logger = LoggerFactory.getLogger(SimpleFactoryBean.class);

    public SimpleBean getSimpleBean() {
        logger.info(">> Instantiating a SimpleBeanImpl.");
        return new SimpleBeanImpl();
    }
}
