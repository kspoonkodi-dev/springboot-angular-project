package com.example.crud.exception;

public class MethodArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 5154410056375173789L;

	MethodArgumentNotValidException(String message){
		super(message);

	}
}
