package com.bao.friend_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "friend_contact")
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fbUrl;
	private  String insUrl;
	private String phoneNum;
	private String email;
}
