package com.usk.demo.exception;

public class CustomerNotFoundException extends RuntimeException{
	
	public CustomerNotFoundException() {
		super();
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}
