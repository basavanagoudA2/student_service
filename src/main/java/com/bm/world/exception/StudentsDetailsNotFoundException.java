package com.bm.world.exception;

public class StudentsDetailsNotFoundException extends RuntimeException{
    public StudentsDetailsNotFoundException() {
        super();
    }

    public StudentsDetailsNotFoundException(String message) {
        super(message);
    }
}
