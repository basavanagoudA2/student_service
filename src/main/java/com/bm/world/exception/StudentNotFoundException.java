package com.bm.world.exception;

import lombok.AllArgsConstructor;

import java.time.LocalTime;


public class StudentNotFoundException extends RuntimeException{
    String errorMessage;
    LocalTime time;

    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }

    public StudentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
