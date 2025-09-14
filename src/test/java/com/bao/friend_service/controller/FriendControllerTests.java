package com.bao.friend_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FriendControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void getFriendById_ShouldReturn404_WhenNotFound() throws Exception {
		var id = 100;

		mockMvc.perform(get(String.format("/api/friends/%d", id)))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.type").value("CLIENT_ERROR"))
				.andExpect(jsonPath("$.message").value(String.format("Friend with id %d not found", id)));
	}

	@Test
	void getFriendById_ShouldReturn400_WhenIdString() throws Exception {
		var id = "test";
		mockMvc.perform(get(String.format("/api/friends/%s", id)))
				.andExpect(status().isBadRequest());
	}
}
