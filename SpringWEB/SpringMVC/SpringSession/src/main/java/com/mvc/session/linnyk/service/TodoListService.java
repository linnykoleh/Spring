package com.mvc.session.linnyk.service;

import java.util.List;

import com.mvc.session.linnyk.model.Todo;

public interface TodoListService {

	List<Todo> findAllTodos();

	void addToList(Todo todo);
}
