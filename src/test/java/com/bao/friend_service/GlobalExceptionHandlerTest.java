package com.bao.friend_service;

import com.bao.friend_service.exception.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {
	private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

	@Test
	void shouldHandleResourceNotFoundException() {
		// given
		var ex = new ResourceNotFoundException("Resource", 123);

		// when
		ResponseEntity<ErrorResponse> response = handler.handleResourceNotFoundException(ex);

		// then
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getType()).isEqualTo(ErrorType.CLIENT_ERROR);
		assertThat(response.getBody().getCode()).isEqualTo(ErrorCode.RESOURCE_NOT_FOUND.getCode());
		assertThat(response.getBody().getMessage()).contains("Resource with id 123 not found");
	}

	@Test
	void shouldHandleUnexpectedException() {
		// given
		var ex = new Exception("Bug");

		// when
		ResponseEntity<ErrorResponse> response = handler.handleUnexpectedException(ex);

		// then
		assertThat(response.getStatusCode().value()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getType()).isEqualTo(ErrorType.SYSTEM_ERROR);
		assertThat(response.getBody().getCode()).isEqualTo(ErrorCode.SYSTEM_ERROR.getCode());
		assertThat(response.getBody().getMessage()).contains("An unexpected error occurred");
	}
}
