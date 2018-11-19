package com.learning.linnyk.jb._2_scope_lifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.linnyk.jb._1_di.beans.Point;
import com.learning.linnyk.jb._2_scope_lifecycle.beans.Triangle;
import com.learning.linnyk.jb._2_scope_lifecycle.beans.TriangleInheritance;
import com.learning.linnyk.jb._2_scope_lifecycle.beans.TriangleLifecycle;
import com.learning.linnyk.jb._2_scope_lifecycle.dependency.Holder;

/**
 * @author LinnykOleh
 */
public class DrawingApp {

	public static void main(String[] args) {

		final AbstractApplicationContext beanFactory = new ClassPathXmlApplicationContext("jb/_2_scope_lifecycle/spring.xml");
		beanFactory.registerShutdownHook(); // enable destroying beans (@PreDestroy, implements DisposableBean)

		final Holder holder = beanFactory.getBean("holder", Holder.class);
		System.out.println(holder.getDependency().getName().equals("spring-oore"));  //true

		final Triangle triangleSingleton1 = (Triangle) beanFactory.getBean("triangleSingleton");
		final Triangle triangleSingleton2 = (Triangle) beanFactory.getBean("triangleSingleton");
		System.out.println((triangleSingleton1 == triangleSingleton2));  //true

		final Triangle trianglePrototype1 = (Triangle) beanFactory.getBean("trianglePrototype");
		final Triangle trianglePrototype2 = (Triangle) beanFactory.getBean("trianglePrototype");
		System.out.println((trianglePrototype1 == trianglePrototype2));  //false

		final TriangleAware triangleAware = (TriangleAware) beanFactory.getBean("triangleAware");
		triangleAware.draw();

		final TriangleInheritance triangleInheritance1 = (TriangleInheritance) beanFactory.getBean("triangle1");
		triangleInheritance1.draw();

		final TriangleInheritance triangleInheritance2 = (TriangleInheritance) beanFactory.getBean("triangle2");
		triangleInheritance2.draw();

		final TriangleLifecycle triangleLifecycle = (TriangleLifecycle) beanFactory.getBean("triangleLifecycle");

		final Point pointWithProperty = (Point) beanFactory.getBean("pointWithProperty");
		System.out.println(pointWithProperty);

	}
}
