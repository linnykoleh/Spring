package com.learning.linnyk.jb._1_di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.linnyk.jb._1_di.beans.Triangle;

/**
 * @author LinnykOleh
 */
public class DrawingApp {

	public static void main(String[] args) {

		final ApplicationContext beanFactory = new ClassPathXmlApplicationContext("jb/_1_di/spring.xml");

		final Triangle triangle1 = (Triangle) beanFactory.getBean("triangle1");
		triangle1.draw();

		final Triangle triangle2 = (Triangle) beanFactory.getBean("triangle2");
		triangle2.draw();

		final Triangle triangle3 = (Triangle) beanFactory.getBean("triangle3");
		triangle3.draw();

		final Triangle triangle4 = (Triangle) beanFactory.getBean("triangle4");
		triangle4.draw();

		final Triangle triangle5 = (Triangle) beanFactory.getBean("triangle5");
		triangle5.draw();

		final Triangle triangleAlias = (Triangle) beanFactory.getBean("triangle-alias");
		triangleAlias.draw();

		final TriangleCollections triangleList = (TriangleCollections) beanFactory.getBean("triangleList");
		triangleList.draw();

		final TriangleCollections triangleSet = (TriangleCollections) beanFactory.getBean("triangleSet");
		triangleSet.draw();

		final TriangleCollections triangleMap = (TriangleCollections) beanFactory.getBean("triangleMap");
		triangleMap.draw();

		final TriangleAutowiringByName triangleAutowiringByName = (TriangleAutowiringByName) beanFactory.getBean("triangleAutowiringByName");
		triangleAutowiringByName.draw();

		final TriangleAutowiringByType triangleAutowiringByType = (TriangleAutowiringByType) beanFactory.getBean("triangleAutowiringByType");
		triangleAutowiringByType.draw();

		final TriangleAutowiringByConstructor triangleAutowiringByConstructor = (TriangleAutowiringByConstructor) beanFactory.getBean("triangleAutowiringByConstructor");
		triangleAutowiringByConstructor.draw();
	}
}
