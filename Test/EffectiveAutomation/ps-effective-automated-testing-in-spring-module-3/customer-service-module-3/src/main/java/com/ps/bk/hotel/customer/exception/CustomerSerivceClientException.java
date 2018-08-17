package com.ps.bk.hotel.customer.exception;

public class CustomerSerivceClientException extends Exception {
	
	private static final long serialVersionUID = -3456841003433599168L;

	public CustomerSerivceClientException() {
	}

	public CustomerSerivceClientException(String message) {
		super(message);
	}

	public CustomerSerivceClientException(Throwable cause) {
		super(cause);
	}

	public CustomerSerivceClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerSerivceClientException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
