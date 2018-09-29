package com.ps.problem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Requested item not found")
public class NotFoundException extends Exception {

	private Long objIdentifier;

	public <T> NotFoundException(Class<T> cls, Long id) {
		super(cls.getSimpleName() + " with id: " + id + " does not exist!");
	}

	public Long getObjIdentifier() {
		return objIdentifier;
	}
}
