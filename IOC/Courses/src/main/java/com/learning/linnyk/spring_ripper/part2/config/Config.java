package com.learning.linnyk.spring_ripper.part2.config;

import com.learning.linnyk.spring_ripper.part2.bean_post_processors.CustomScopeRegistryBeanFactoryPostProcessor;
import com.learning.linnyk.spring_ripper.part2.screensaver.ColorFrame;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.awt.*;
import java.util.Random;

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

}
