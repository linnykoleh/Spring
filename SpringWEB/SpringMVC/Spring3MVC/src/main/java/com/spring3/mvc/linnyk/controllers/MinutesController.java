package com.spring3.mvc.linnyk.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring3.mvc.linnyk.model.Activity;
import com.spring3.mvc.linnyk.model.Exercise;
import com.spring3.mvc.linnyk.service.ExerciseService;

@Controller
public class MinutesController {

	@Autowired
	private ExerciseService exerciseService;

	/*
	 *
	 * ModelAttribute("exercise") - соединяет `Exercise exercise` с формой на addMinutes.jsp `<form:form commandName="exercise">`
	 *
	 *
	 * 		- return "forward:addMoreMinutes" -  перенапрявляет реквест
	 * 		- return "redirect:addMoreMinutes" - закрывает реквест и создает новый реквест
	 */
	@RequestMapping(value = "/addMinutes")
	public String addMinutes(@ModelAttribute("exercise") Exercise exercise) {

		System.out.println("exercise: " + exercise.getMinutes());

		return "redirect:addMoreMinutes.html";
	}

	@RequestMapping(value = "/addMoreMinutes")
	public String addMoreMinutes(@ModelAttribute("exercise") Exercise exercise) {

		System.out.println("exercising: " + exercise.getMinutes());

		return "addMinutes";  //Возвращает имя view
	}

	@RequestMapping(value = "/activities", method = RequestMethod.GET)
	public @ResponseBody List<Activity> findAllActivities(){
		return exerciseService.findAllActivities();
	}

}
