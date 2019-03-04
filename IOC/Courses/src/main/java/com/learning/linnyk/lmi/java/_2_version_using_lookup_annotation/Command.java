package com.learning.linnyk.lmi.java._2_version_using_lookup_annotation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Command {

	public void execute() {
		System.out.println("Command: " + this.hashCode());
	}

}
