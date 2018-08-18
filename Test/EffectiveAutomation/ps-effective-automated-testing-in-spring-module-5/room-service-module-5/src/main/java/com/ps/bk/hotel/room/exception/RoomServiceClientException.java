package com.ps.bk.hotel.room.exception;

/**
 * Exception thrown when client request has an invalid format or contains
 * invalid data.
 * 
 * @author williamkorando
 *
 */
public class RoomServiceClientException extends RuntimeException {

	private static final long serialVersionUID = 2228884659709981344L;

	public RoomServiceClientException() {
	}

	public RoomServiceClientException(String message) {
		super(message);
	}

	public RoomServiceClientException(Throwable cause) {
		super(cause);
	}

	public RoomServiceClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoomServiceClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
