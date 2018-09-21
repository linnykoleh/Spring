package com.spring4.mvc.linnyk.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring4.mvc.linnyk.model.Event;

@RestController
public class EventReportController {

	@RequestMapping("/events")
	public List<Event> getEvents(){
		List<Event> events = new ArrayList<Event>();
		events.add(new Event("111"));
		events.add(new Event("222"));
		events.add(new Event("333"));

		return events;
	}
}
