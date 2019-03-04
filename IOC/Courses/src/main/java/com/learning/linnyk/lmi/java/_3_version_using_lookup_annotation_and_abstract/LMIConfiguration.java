package com.learning.linnyk.lmi.java._3_version_using_lookup_annotation_and_abstract;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.learning.linnyk.lmi.java")
public class LMIConfiguration {

	@Bean
	@Qualifier("myCommand")
	@Scope("prototype")
	public Command command() {
		return new Command();
	}

	@Bean
	public CommandManager commandManager() {
		return new CommandManager() {
			@Override
			Command createCommand() {
				return command();
			}
		};
	}
}
