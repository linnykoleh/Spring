package com.learning.linnyk.state.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@Scope("prototype")
//@Scope("singleton")
public class StatefulBean {

    private List<String> sharedState = new CopyOnWriteArrayList<>();

    public void addToState(String sharedState) {
        this.sharedState.add(sharedState);
    }

    public List<String> getSharedState() {
        return sharedState;
    }
}
