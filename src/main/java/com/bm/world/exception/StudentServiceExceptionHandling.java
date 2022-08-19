package com.bm.world.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentServiceExceptionHandling {
	
	@ExceptionHandler(value = StudentNotFoundException.class)
	public ResponseEntity<ErrorMessage> studentNotFoundExceptionHandling(StudentNotFoundException notFoundException) {
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setMessage(notFoundException.getErrorMessage());
        errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

}
