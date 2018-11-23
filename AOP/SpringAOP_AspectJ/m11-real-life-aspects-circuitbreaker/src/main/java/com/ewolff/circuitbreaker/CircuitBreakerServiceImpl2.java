package com.ewolff.circuitbreaker;

import org.springframework.stereotype.Service;

@Service
public class CircuitBreakerServiceImpl2 implements CircuitBreakerService {

	private int counter;

	@CircuitBreaker
	public void erroneousMethod() {
		counter++;
		if (counter < 2) {
			throw new RuntimeException();
		}
	}

}
