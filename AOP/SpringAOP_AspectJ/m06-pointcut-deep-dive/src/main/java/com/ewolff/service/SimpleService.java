package com.ewolff.service;

import org.springframework.stereotype.Service;

import annotation.Trace;

@Service
public class SimpleService {

	public void doSomething() {
	}

	@Trace
	public void annotated() {
	}
}
