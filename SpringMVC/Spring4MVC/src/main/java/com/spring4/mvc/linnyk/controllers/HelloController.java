package com.spring4.mvc.linnyk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping(value = "/gretting")
	public String sayHello(Model model) {
		model.addAttribute("greeting", "Hello world Spring 4!");
		return "hello"; 	//Возвращает имя view
	}
}
