package com.ps.bk.hotel.booking.exception;

public class BookingServiceClientException extends RuntimeException {

	private static final long serialVersionUID = -7451786047069080251L;

	public BookingServiceClientException() {

	}

	public BookingServiceClientException(String message) {
		super(message);
	}

	public BookingServiceClientException(Throwable cause) {
		super(cause);
	}

	public BookingServiceClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingServiceClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
