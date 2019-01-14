package com.learning.linnyk.initialization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.learning.linnyk.initialization.beans.order.Rating;
import com.learning.linnyk.initialization.config.AppInitConfiguration;

@Component
public class App {

	private final List<Rating> ratings;

	@Autowired
	public App(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public static void main(String[] args) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(AppInitConfiguration.class);
		System.out.println("#7 Initialized");

		//#1 BeanFactoryPostProcessor
		//#2 Constructor
		//#3 Setter
		//#4 Before BeanPostProcessor
		//#5 PostConstruct
		//#6 After BeanPostProcessor
		//#7 Initialized

		final App bean = context.getBean(App.class);
		System.out.println(bean.ratings); // [Excellent{order 1}, Good{order 2}, Average{order 3}]
	}
}
