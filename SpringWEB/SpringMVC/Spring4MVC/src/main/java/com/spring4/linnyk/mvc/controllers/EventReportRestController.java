package com.spring4.linnyk.mvc.controllers;

import com.spring4.linnyk.mvc.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController // @Controller + @ResponseBody
public class EventReportRestController {

    /**
     * http://localhost:8087/spring-mvc/rest_events
     */
	@RequestMapping(value = "/rest_events", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getEvents(){
		final List<Event> events = new ArrayList<>();

		events.add(new Event("Java User Group"));
		events.add(new Event("Angular User Group"));
		events.add(new Event("JEEConf"));

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

    /**
     * http://localhost:8087/spring-mvc/rest_event/JEEConf
     */
    @RequestMapping(value = "/rest_event/{name}", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent1(@PathVariable("name") String event){
        return new ResponseEntity<>(new Event(event), HttpStatus.OK);
    }

    /**
     * http://localhost:8087/spring-mvc/rest_event?eventName=Devoxx
     */
    @RequestMapping(value = "/rest_event", method = RequestMethod.GET)
    public ResponseEntity<Event> getEvent2(@RequestParam("eventName") String name) {
        return new ResponseEntity<>(new Event(name), HttpStatus.OK);
    }

    /**
     * Create new Event
     * {"name":"JUG.com"}
     *
     * Content-Type:application/json
     */
    @RequestMapping(value = "/rest_event", method = RequestMethod.POST)
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        System.out.println(event);
        if(Objects.isNull(event)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * Update Event
     * {"name":"Docker Users"}
     *
     * Content-Type:application/json
     */
    @RequestMapping(value = "/rest_event", method = RequestMethod.PUT)
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        System.out.println(event);
        if(Objects.isNull(event)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    /**
     * Delete Event
     * {"name":"SQL Developers"}
     *
     * Content-Type:application/json
     */
    @RequestMapping(value = "/rest_event", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEvent(@RequestBody Event event) {
        System.out.println(event);
        if(Objects.isNull(event)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
