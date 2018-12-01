package com.learning.linnyk;

import com.learning.linnyk.beans.order.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.config.AppConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class App {

    @Autowired
    private List<Rating> ratings;

	public static void main(String[] args) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		System.out.println("#7 Initialized");

        final App bean = context.getBean(App.class);
        System.out.println(bean.ratings); // [Excellent{order 1}, Good{order 2}, Average{order 3}]
    }
}
