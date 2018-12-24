package com.learning.linnyk.spring_ripper.part1.context;

import com.learning.linnyk.spring_ripper.part1.bean.Quoter;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author LinnykOleh
 */
public class PropertyFileApplicationContext extends GenericApplicationContext {

	public PropertyFileApplicationContext(String fileName) {
		final PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
		final int i = reader.loadBeanDefinitions(fileName);
		System.out.println("found " + i + " beans");
		refresh();
	}

	public static void main(String[] args) {
		final PropertyFileApplicationContext context = new PropertyFileApplicationContext("sp/context.properties");
		context.getBean(Quoter.class).sayQuote();
	}


}
