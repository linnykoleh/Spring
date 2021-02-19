package com.learning.linnyk.pair.beans;

import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecondBean {

    @Autowired
    private MainBean bean;

    public void render() {
        Pair<String, Integer> propPair = bean.getPropPair();
        System.out.println("Key: " + propPair.getKey() + " Value: " + propPair.getValue());
    }

}
