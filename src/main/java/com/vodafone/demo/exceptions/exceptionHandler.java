package com.vodafone.demo.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class exceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handle(NotFoundException notFoundException){
        return "this identifier not found";
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handle(ConstraintViolationException constraintViolationException){
        return constraintViolationException.getMessage();
    }
}
