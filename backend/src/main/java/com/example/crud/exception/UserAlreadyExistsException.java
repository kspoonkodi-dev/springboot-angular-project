package com.example.crud.exception;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -3087962469134386857L;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
