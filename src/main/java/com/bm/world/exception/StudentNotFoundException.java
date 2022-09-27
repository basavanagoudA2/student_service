package com.bm.world.exception;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentNotFoundException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String errorMessage;
    LocalTime time;
	public StudentNotFoundException() {
		super();
	}
	public StudentNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public StudentNotFoundException(String message) {
		this.errorMessage=message;
	}
	public StudentNotFoundException(Throwable cause) {
		super(cause);
	}

    
}
