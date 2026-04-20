package com.example.crud.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crud.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InstructorNotEligibleException.class)
	public ResponseEntity<ErrorResponse> handleInstructorNotEligible(InstructorNotEligibleException ex) {

		ErrorResponse error = new ErrorResponse(
	            ex.getMessage(),
	            HttpStatus.FORBIDDEN.value(),
	            LocalDateTime.now()
	    );
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}


	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleUserExists(UserAlreadyExistsException ex) {

		ErrorResponse error = new ErrorResponse(
	            ex.getMessage(),
	            HttpStatus.BAD_REQUEST.value(),
	            LocalDateTime.now()
	    );
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleArgumentNotValid(MethodArgumentNotValidException ex) {

		ErrorResponse error = new ErrorResponse(
	            ex.getMessage(),
	            HttpStatus.BAD_REQUEST.value(),
	            LocalDateTime.now()
	    );
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleIntegrityViolation(DataIntegrityViolationException ex) {

		ErrorResponse error = new ErrorResponse(
	            ex.getMessage(),
	            HttpStatus.BAD_REQUEST.value(),
	            LocalDateTime.now()
	    );
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ex) {

		ErrorResponse error = new ErrorResponse(
	            ex.getMessage(),
	            HttpStatus.NOT_FOUND.value(),
	            LocalDateTime.now()
	    );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

	    ErrorResponse error = new ErrorResponse(
	            "Something went wrong",
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            LocalDateTime.now()
	    );

	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
