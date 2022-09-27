package com.bm.world.exception;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

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
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(value =ConstraintViolationException.class )
	public ResponseEntity<ApiError> handleFieldsValidationExceptions(ConstraintViolationException exception) {
		String errorMessage=new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
		ApiError apiError=new ApiError(errorMessage, LocalTime.now(), 1000l);
		return new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
		
	}

}
