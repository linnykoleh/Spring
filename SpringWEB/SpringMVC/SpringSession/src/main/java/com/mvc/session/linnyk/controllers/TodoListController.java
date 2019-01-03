package com.mvc.session.linnyk.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mvc.session.linnyk.model.Todo;
import com.mvc.session.linnyk.model.Visitor;
import com.mvc.session.linnyk.service.TodoListService;

@RestController
@SessionAttributes("visitor")
public class TodoListController {

	private final TodoListService todoListService;

	@Autowired
	public TodoListController(TodoListService todoListService) {
		this.todoListService = todoListService;
	}

	@ModelAttribute("visitor")
	public Visitor getVisitor(HttpServletRequest request) {
		return new Visitor(request.getSession().getId());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Todo addTodo(@ModelAttribute("visitor") Visitor visitor, @RequestBody Todo todoToAdd) {
		final Todo todo = new Todo(todoToAdd.getName(), todoToAdd.getDescription(), visitor);
		todoListService.addToList(todo);
		return todo;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<Todo> getAllTodos() {
		return todoListService.findAllTodos();
	}
}
