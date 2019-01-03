package com.mvc.session.linnyk.model;

public class Todo {

	private String name;
	private String description;
	private Visitor visitor;

	public Todo() {
	}

	public Todo(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Todo(String name, String description, Visitor visitor) {
		this.name = name;
		this.description = description;
		this.visitor = visitor;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Visitor getVisitor() {
		return visitor;
	}
}
