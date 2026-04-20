package com.example.crud.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1161166870204236378L;

	public EntityNotFoundException(String message)
	{
		super(message);
	}

}
