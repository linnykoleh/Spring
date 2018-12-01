package com.learning.linnyk.beans.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class Average implements Rating {

    @Override
    public String toString() {
        return "Average{order 3}";
    }
}
