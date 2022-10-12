package com.bm.world.exception;

import java.io.OutputStream;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class StudentServiceExceptionHandling {

    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundExceptionHandling(StudentNotFoundException notFoundException) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(notFoundException.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleFieldsValidationExceptions(ConstraintViolationException exception) {
        String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(errorMessage);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(errorMessage);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
