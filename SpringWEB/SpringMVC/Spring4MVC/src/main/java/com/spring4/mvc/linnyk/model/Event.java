package com.spring4.mvc.linnyk.model;

public class Event {

	private String event;

	public Event(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Event{" +
				"event='" + event + '\'' +
				'}';
	}
}
