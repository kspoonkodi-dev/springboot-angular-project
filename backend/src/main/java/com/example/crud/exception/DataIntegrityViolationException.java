package com.example.crud.exception;

public class DataIntegrityViolationException extends RuntimeException{

	private static final long serialVersionUID = 2669433420048907778L;

	public DataIntegrityViolationException(String message) {
		super(message);

	}

}
