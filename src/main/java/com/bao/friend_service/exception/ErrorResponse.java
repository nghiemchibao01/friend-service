package com.bao.friend_service.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
	private final ErrorType type;
	private final int code;
	private final String message;
	private final LocalDateTime timestamp;

	public ErrorResponse(
			ErrorType type,
			ErrorCode errorCode,
			String message) {
		this.type = type;
		this.code = errorCode.getCode();
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}
}
