package com.mvc.session.linnyk.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.mvc.session.linnyk.model.Todo;
import com.mvc.session.linnyk.service.TodoListService;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class TodoListServiceImpl implements TodoListService {

	private List<Todo> todos = new CopyOnWriteArrayList<>();

	@Override
	public void addToList(Todo todo) {
		todos.add(todo);
	}

	@Override
	public List<Todo> findAllTodos() {
		return new ArrayList<>(todos);
	}

}
