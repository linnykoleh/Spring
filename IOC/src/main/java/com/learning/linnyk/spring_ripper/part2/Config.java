package com.learning.linnyk.spring_ripper.part2;

import java.awt.Color;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.learning.linnyk.spring_ripper.part2.screensaver.ColorFrame;

/**
 * @author LinnykOleh
 */
@Configuration
@ComponentScan("com.learning.linnyk.spring_ripper.part2.screensaver")
public class Config {

	@Bean
	@Scope(value = "periodical")
	public Color color(){
		final Random random = new Random();
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	@Bean
	public ColorFrame frame(){
		return new ColorFrame() {
			@Override
			protected Color getColor() {
				return color();
			}
		};
	}

	@Bean
	public CustomScopeRegistryBeanFactoryPostProcessor customScopeRegistryBeanFactoryPostProcessor(){
		return new CustomScopeRegistryBeanFactoryPostProcessor();
	}

	public static void main(String[] args) throws InterruptedException {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		while (true) {
			context.getBean(ColorFrame.class).showOnRandomPlace();
			Thread.sleep(300);
		}
	}
}
