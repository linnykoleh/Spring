package com.learning.linnyk.lmi.java;

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
