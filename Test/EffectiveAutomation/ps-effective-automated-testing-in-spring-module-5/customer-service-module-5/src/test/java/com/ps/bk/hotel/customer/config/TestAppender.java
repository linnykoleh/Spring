package com.ps.bk.hotel.customer.config;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class TestAppender extends AppenderBase<LoggingEvent> {
	static List<LoggingEvent> events = new ArrayList<>();

	@Override
	protected void append(LoggingEvent e) {
		if(e.getLoggerName().endsWith(ControllerLoggingAspect.class.getSimpleName())){
			events.add(e);
		}
	}

	public static List<LoggingEvent> getEvents() {
		return events;
	}

	public static void clearEvents() {
		events.clear();
	}
}
