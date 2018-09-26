package com.spring4.linnyk.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring4.linnyk.mvc.model.Event;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("event")
public class EventController {

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String displayEventPage(Model model) {
		final Event event = new Event("Java user group");

		model.addAttribute("event", event);

		return "event"; //returns a name of the view
	}

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public String processEvent(@ModelAttribute("event") Event event){
		System.out.println(event);

		return "redirect:index.html";
	}

}
