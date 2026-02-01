package com.example.demo.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.InvalidId;
import com.example.demo.exception.InvalidMobileNumber;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidMobileNumber.class)
	public ResponseEntity<?> invalidMobileNumber(InvalidMobileNumber e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
				.body(e.getMessage());	
	}
	@ExceptionHandler(InvalidId.class)
	public ResponseEntity<?> invalidId(InvalidId e){
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
				.body(e.getMessage());	
	}
}
