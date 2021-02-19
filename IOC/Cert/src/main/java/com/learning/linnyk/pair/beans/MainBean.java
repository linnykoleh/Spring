package com.learning.linnyk.pair.beans;

import lombok.Getter;
import org.apache.commons.math3.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MainBean {

    private Pair<String, Integer> propPair;

    @Autowired
    public MainBean(@Value("${prop1}") String prop) {
        String[] split = prop.split("-");
        propPair = new Pair<>(split[0], Integer.parseInt(split[1]));
    }

    // Or thought method injection
    @Autowired
    public void setUpPair(@Value("${prop1}") String prop) {
        String[] split = prop.split("-");
        propPair = new Pair<>(split[0], Integer.parseInt(split[1]));
    }

}
