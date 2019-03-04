package com.learning.linnyk.lmi.java._2_version_using_lookup_annotation;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class CommandManager {

	@Lookup
	public Command createCommand() {
		return null;
	}

	public void process() {
		System.out.println("CommandManager: " + this.hashCode());
		final Command command = createCommand();
		command.execute();
	}

}
