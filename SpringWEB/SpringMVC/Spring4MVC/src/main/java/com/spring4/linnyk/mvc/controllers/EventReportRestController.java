package com.spring4.linnyk.mvc.controllers;

import com.spring4.linnyk.mvc.model.Event;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // @Controller + @ResponseBody
public class EventReportRestController {

	@RequestMapping("/events")
	public List<Event> getEvents(){
		final List<Event> events = new ArrayList<>();

		events.add(new Event("Java User Group"));
		events.add(new Event("Angular User Group"));
		events.add(new Event("JEEConf"));

		return events;
	}
}
