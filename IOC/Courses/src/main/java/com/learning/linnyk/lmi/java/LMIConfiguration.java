package com.learning.linnyk.lmi.java;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
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
