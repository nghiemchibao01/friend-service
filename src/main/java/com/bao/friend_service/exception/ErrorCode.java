package com.bao.friend_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
	RESOURCE_NOT_FOUND(1001, "Resource not found"),
	FRIEND_ALREADY_EXISTS(1002, "Friend already exists"),
	CONTACT_NOT_FOUND(2001, "Contact not found"),
	INVALID_PARAMETER_TYPE(3001, "Parameter invalid"),
	VALIDATION_ERROR(9001, "Validation failed"),
	SYSTEM_ERROR(9003, "Unexpected system error");

	private final int code;
	private final String defaultMessage;

	ErrorCode(int code, String defaultMessage) {
		this.code = code;
		this.defaultMessage = defaultMessage;
	}
}
