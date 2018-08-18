package com.ps.bk.hotel.room.exception;

/**
 * Exception thrown when an internal server error is thrown while attempting to
 * process a request.
 * 
 * @author williamkorando
 *
 */
public class RoomServiceException extends RuntimeException {
	private static final long serialVersionUID = -5539671745329452363L;

	public RoomServiceException() {
	}

	public RoomServiceException(String message) {
		super(message);
	}

	public RoomServiceException(Throwable cause) {
		super(cause);
	}

	public RoomServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
