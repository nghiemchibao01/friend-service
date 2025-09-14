package com.bao.friend_service.model;

import com.bao.friend_service.model.validation.OnCreate;
import com.bao.friend_service.model.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

@Data
public class ParentModel {
	@Null(groups = OnCreate.class, message = "Id must be null when creating")
	@NotNull(groups = OnUpdate.class, message = "Id is required when updating")
	private Long id;
	private String father;
	private String mother;
}
