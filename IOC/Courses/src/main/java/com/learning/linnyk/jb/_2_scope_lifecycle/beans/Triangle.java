package com.learning.linnyk.jb._2_scope_lifecycle.beans;

/**
 * @author LinnykOleh
 */
public class Triangle {

	private String type;
	private String color;

	public Triangle(String type, String color) {
		this.type = type;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Triangle{" +
				"type='" + type + '\'' +
				", color='" + color + '\'' +
				'}';
	}
}
