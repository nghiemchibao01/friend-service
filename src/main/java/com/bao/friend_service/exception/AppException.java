package com.bao.friend_service.exception;

import lombok.Getter;

@Getter
public abstract class AppException extends RuntimeException {
	private final ErrorType type;
	private final ErrorCode errorCode;

	public AppException(ErrorType type, ErrorCode errorCode) {
		super(errorCode.getDefaultMessage());
		this.type = type;
		this.errorCode = errorCode;
	}

	public AppException(ErrorType type, ErrorCode errorCode, String message) {
		super(message);
		this.type = type;
		this.errorCode = errorCode;
	}

	public AppException(String message, ErrorType type, ErrorCode errorCode) {
		super(message);
		this.type = type;
		this.errorCode = errorCode;
	}
}
