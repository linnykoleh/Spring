package com.learning.linnyk.restfulwebservices.user.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest webRequest) {
		final ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(true));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
		final ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(true));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		final ExceptionResponse exceptionResponse =
				new ExceptionResponse(new Date(), "Validation failed", ex.getBindingResult().toString());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}
}
