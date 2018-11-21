package com.learning.linnyk.jb._2_scope_lifecycle.beans.qualifier;

public class TestBean {

	private String name;

	public TestBean(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TestBean{" +
				"name='" + name + '\'' +
				'}';
	}
}
