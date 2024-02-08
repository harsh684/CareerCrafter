package com.hexaware.careercrafterfinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handler(Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
	}
	
}
