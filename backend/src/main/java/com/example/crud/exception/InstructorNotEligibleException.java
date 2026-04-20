package com.example.crud.exception;

public class InstructorNotEligibleException extends RuntimeException {

 	private static final long serialVersionUID = -253332556299005098L;

	public InstructorNotEligibleException(String message) {
        super(message);
    }
}