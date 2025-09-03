package com.usk.demo.exception;

import com.usk.demo.exception.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<com.usk.demo.exception.ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
	    com.usk.demo.exception.ErrorDetails errorDetails = new com.usk.demo.exception.ErrorDetails(
	        HttpStatus.NOT_FOUND.value(),
	        ex.getMessage(),
	        request.getDescription(false)
	    );
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<com.usk.demo.exception.ErrorDetails> handleBusinessLogic(BusinessLogicException ex, WebRequest request) {
        com.usk.demo.exception.ErrorDetails errorDetails = new com.usk.demo.exception.ErrorDetails(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<com.usk.demo.exception.ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
        com.usk.demo.exception.ErrorDetails errorDetails = new ErrorDetails(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
