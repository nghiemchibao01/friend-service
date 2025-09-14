package com.bao.friend_service.exception;

public class ResourceNotFoundException extends AppException {
	public ResourceNotFoundException(String resource, Object id) {
		super(
			ErrorType.CLIENT_ERROR,
			ErrorCode.RESOURCE_NOT_FOUND,
			resource + " with id " + id + " not found");
	}
}
