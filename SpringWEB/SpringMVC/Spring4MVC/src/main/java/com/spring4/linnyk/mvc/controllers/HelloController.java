package com.spring4.linnyk.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping(value = "/greeting")
	public ModelAndView sayHello() {
		final ModelAndView modelAndView = new ModelAndView("hello");
		modelAndView.addObject("greeting", "Hello world Spring 4!");
		return modelAndView;
	}
}
