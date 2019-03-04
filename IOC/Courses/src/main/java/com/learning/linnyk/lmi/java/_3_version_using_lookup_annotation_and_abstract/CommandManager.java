package com.learning.linnyk.lmi.java._3_version_using_lookup_annotation_and_abstract;

import org.springframework.beans.factory.annotation.Lookup;

public abstract class CommandManager {

	@Lookup
	abstract Command createCommand();

	public void process() {
		System.out.println("CommandManager: " + this.hashCode());
		final Command command = createCommand();
		command.execute();
	}

}
