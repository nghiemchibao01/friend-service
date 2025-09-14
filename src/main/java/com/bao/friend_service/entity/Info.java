package com.bao.friend_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "friend_info")
public class Info {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
