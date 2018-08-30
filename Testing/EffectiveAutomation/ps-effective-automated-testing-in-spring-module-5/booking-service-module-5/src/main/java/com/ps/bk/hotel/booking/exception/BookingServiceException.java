package com.ps.bk.hotel.booking.exception;

public class BookingServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 8156685503091223523L;

	public BookingServiceException() {

	}

	public BookingServiceException(String message) {
		super(message);
	}

	public BookingServiceException(Throwable cause) {
		super(cause);
	}

	public BookingServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookingServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
