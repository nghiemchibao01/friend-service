package com.bao.friend_service.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger businessLogger = LoggerFactory
			.getLogger("BusinessLogger");
	private static final Logger systemLogger = LoggerFactory
			.getLogger("SystemLogger");

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
		var response = new ErrorResponse(
				ex.getType(),
				ex.getErrorCode(),
				ex.getMessage());

		businessLogger.error("Business exception: {}", ex.getMessage(), ex);

		var status = mapErrorTypeToStatus(ex.getType());

		return ResponseEntity.status(status).body(response);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
			ResourceNotFoundException ex) {
		var response = new ErrorResponse(
				ex.getType(),
				ex.getErrorCode(),
				ex.getMessage());

		businessLogger.error("Business exception: {}", ex.getMessage(), ex);

		var status = mapErrorTypeToStatus(ex.getType());

		return ResponseEntity.status(status).body(response);
	}


	// Handles request body validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.toList();

		businessLogger.error("Business exception: {}", ex.getMessage(), ex);

		ErrorResponse response = new ErrorResponse(
				ErrorType.CLIENT_ERROR,
				ErrorCode.VALIDATION_ERROR,
				String.join("; ", errors)
		);

		return ResponseEntity.badRequest().body(response);
	}

	// Handles parameter validation errors
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolation(
			ConstraintViolationException ex) {
		List<String> errors = ex.getConstraintViolations().stream()
				.map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
				.toList();
		businessLogger.error("Business exception: {}", ex.getMessage(), ex);

		ErrorResponse response = new ErrorResponse(
				ErrorType.CLIENT_ERROR,
				ErrorCode.VALIDATION_ERROR,
				String.join("; ", errors)
		);

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String paramName = ex.getName();
		String requiredType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown";
		String message = String.format("Parameter '%s' must be of type %s", paramName, requiredType);

		ErrorResponse response = new ErrorResponse(
				ErrorType.VALIDATION_ERROR,
				ErrorCode.INVALID_PARAMETER_TYPE,
				message
		);

		businessLogger.error("Type mismatch: {}", message, ex);

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleUnexpectedException(Exception ex) {
		systemLogger.error("Unhandled exception occurred", ex);
		systemLogger.error("Class: {}", ex.getClass().getName());

		ErrorResponse response = new ErrorResponse(
				ErrorType.SYSTEM_ERROR,
				ErrorCode.SYSTEM_ERROR,
				"An unexpected error occurred"
		);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	private HttpStatus mapErrorTypeToStatus(ErrorType type) {
		return switch (type) {
			case CLIENT_ERROR -> HttpStatus.NOT_FOUND;
			case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;
			case BUSINESS_ERROR -> HttpStatus.UNPROCESSABLE_ENTITY;
			default -> HttpStatus.INTERNAL_SERVER_ERROR;
		};
	}
}
