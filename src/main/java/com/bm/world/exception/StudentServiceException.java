package com.bm.world.exception;

public class StudentServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentServiceException() {
		super();
	}

	public StudentServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public StudentServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentServiceException(String message) {
		super(message);
	}

	public StudentServiceException(Throwable cause) {
		super(cause);
	}

}
