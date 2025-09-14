package com.bao.friend_service.model;

import com.bao.friend_service.model.validation.OnCreate;
import com.bao.friend_service.model.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDate;

@Data
public class InfoModel {
	@Null(groups = OnCreate.class, message = "Id must be null when creating")
	@NotNull(groups = OnUpdate.class, message = "Id is required when updating")
	private Long id;
	@NotNull(groups = OnCreate.class, message = "Full name is required when created")
	private  String fullName;
	private String nickName;
	private LocalDate birthDay;
	private String address;
	private String hobby;
	private String elementarySchool;
	private String middleSchool;
	private String highSchool;
	private String university;
}
