package com.learning.linnyk.beans.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class Good implements Rating {

    @Override
    public String toString() {
        return "Good{order 2}";
    }
}
