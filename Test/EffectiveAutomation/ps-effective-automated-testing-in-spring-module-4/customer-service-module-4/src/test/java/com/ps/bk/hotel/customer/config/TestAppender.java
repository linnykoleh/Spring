package com.ps.bk.hotel.customer.config;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class TestAppender extends AppenderBase<LoggingEvent> {

	private static final List<LoggingEvent> events = new ArrayList<>();

	@Override
	protected void append(LoggingEvent e) {
		if (e.getLoggerName().endsWith(ControllerLoggingAspect.class.getSimpleName())) {
			events.add(e);
		}
	}

	static List<LoggingEvent> getEvents() {
		return events;
	}

	static void clearEvents() {
		events.clear();
	}
}
