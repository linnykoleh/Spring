package com.ps.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class SimpleBeanImpl implements SimpleBean {

    public SimpleBeanImpl() {
        System.out.println("[SimpleBeanImpl instantiation]");
    }

    @Override
    public String toString() {
        return "SimpleBeanImpl{ code: " + hashCode() + "}";
    }
}
