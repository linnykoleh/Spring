package com.ps.bk.hotel.booking.exception;

public class BookingServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 8156685503091223523L;

	public BookingServiceException() {
		// TODO Auto-generated constructor stub
	}

	public BookingServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BookingServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BookingServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BookingServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
