package com.loanApplication.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Globalxception extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(InvalidExpiredTokenException.class)
    public ResponseEntity<Object> invalidExpiredTokenExceptionException(
    		InvalidExpiredTokenException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now()); 
        body.put("message", "Invalid Token"); 
       

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN); 
    }
	 
	@ExceptionHandler(LoanDoesNotExistException.class)
    public ResponseEntity<Object> loanDoesNotExistException(
    		LoanDoesNotExistException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "This Loan does not Exist");
       

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
}
