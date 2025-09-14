package com.bao.friend_service.model;

import com.bao.friend_service.model.validation.OnCreate;
import com.bao.friend_service.model.validation.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactModel {
	@Null(groups = OnCreate.class, message = "Id must be null when creating")
	@NotNull(groups = OnUpdate.class, message = "Id is required when updating")
	private Long id;
	@Size(max = 100, message = "Facebook link too long")
	private String fb;
	@Size(max = 100, message = "Instagram link too long")
	private  String ins;
	@Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
	private String phone;
	@Email(message = "Invalid email format")
	private String email;
}
