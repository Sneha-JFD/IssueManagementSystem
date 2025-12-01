package com.growvenus.issue_management;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.growvenus.issue_management.exception.IssueNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error -> 
	            errors.put(error.getField(), error.getDefaultMessage())
	        );
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(IssueNotFoundException.class)
	    public ResponseEntity<Map<String, String>> handleIssueNotFound(IssueNotFoundException ex) {
	        Map<String, String> error = new HashMap<>();
	        error.put("message", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
}
