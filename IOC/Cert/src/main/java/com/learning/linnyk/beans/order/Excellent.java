package com.learning.linnyk.beans.order;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Excellent implements Rating {

    @Override
    public String toString() {
        return "Excellent{order 1}";
    }
}
