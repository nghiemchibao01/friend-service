package com.bao.friend_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FriendModel {
	private Long id;
	private ContactModel contact;
	private ParentModel parent;
	@NotNull(message = "Info is required")
	private InfoModel info;
}
