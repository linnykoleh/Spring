package com.ewolff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlainService {

	@Autowired
	private ErroneousService erroneousService;

	public void doIt(int i) {
		erroneousService.returnInt();
		erroneousService.throwException();
	}

}
