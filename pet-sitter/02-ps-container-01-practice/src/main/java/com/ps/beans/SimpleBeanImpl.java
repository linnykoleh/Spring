package com.ps.beans;

public class SimpleBeanImpl implements SimpleBean {

    public SimpleBeanImpl() {
        System.out.println("[SimpleBeanImpl instantiation]");
    }

    @Override
    public String toString() {
        return "SimpleBeanImpl{ code: " + hashCode() + "}";
    }
}
