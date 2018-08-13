package com.learning.linnyk.pluralsight.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learning.linnyk.pluralsight.java.repository.CustomRepository;
import com.learning.linnyk.pluralsight.java.repository.HibernateCustomRepository;
import com.learning.linnyk.pluralsight.java.service.CustomerService;
import com.learning.linnyk.pluralsight.java.service.CustomerServiceImpl;

/**
 * @author LinnykOleh
 */
public class Application {

	public static void main(String[] args) {
		final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		final CustomerService customerServiceSetterInjection = context.getBean("customerServiceSetterInjection", CustomerService.class);
		System.out.println(customerServiceSetterInjection.findAll());

		final CustomerService customerServiceConstructorInjection = context.getBean("customerServiceConstructorInjection", CustomerService.class);
		System.out.println(customerServiceConstructorInjection.findAll());

		final CustomerService customerServiceImpl1 = context.getBean("customerServiceImpl", CustomerService.class);
		System.out.println(customerServiceImpl1.findAll());

		final CustomerService customerServiceImpl2 = context.getBean("customerServiceImpl", CustomerService.class);
		System.out.println("Singletons are same: " + (customerServiceImpl1 == customerServiceImpl2)); //true

		final CustomRepository customRepository1 = context.getBean("hibernateCustomRepository", CustomRepository.class);
		final CustomRepository customRepository2 = context.getBean("hibernateCustomRepository", CustomRepository.class);

		System.out.println("Prototypes are different: " + (customRepository1 == customRepository2)); //false

		System.out.println("Property timeout injection: " + ((CustomerServiceImpl)customerServiceImpl1).getTimeout()); //1000
		System.out.println("Property dbUsername injection: " + ((HibernateCustomRepository)customRepository1).getDbUsername()); //derbyUsername

	}
}
