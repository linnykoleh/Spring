package com.learning.linnyk.services;

import org.springframework.stereotype.Component;

@Component
public class MyClass {

	public void doIt() {
		System.out.println("MyClass doIt");
	}

	private void privateMethod() {
		//Will never be executed due-to private method
		System.out.println("privateMethod");
	}
}
