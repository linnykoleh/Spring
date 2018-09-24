package com.spring4.linnyk.mvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring4.linnyk.mvc.model.Event;

@RestController
public class EventReportRestController {

	@RequestMapping("/events")
	public List<Event> getEvents(){
		final List<Event> events = new ArrayList<>();

		events.add(new Event("111"));
		events.add(new Event("222"));
		events.add(new Event("333"));

		return events;
	}
}
