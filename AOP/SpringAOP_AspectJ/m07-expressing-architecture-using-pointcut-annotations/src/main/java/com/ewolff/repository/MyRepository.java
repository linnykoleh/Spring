package com.ewolff.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepository {

	public void doIt() {
	}

	public void throwsException() {
		throw new RuntimeException();
	}

}
