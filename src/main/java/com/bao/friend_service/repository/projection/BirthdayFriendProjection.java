package com.bao.friend_service.repository.projection;

import java.time.LocalDate;

public interface BirthdayFriendProjection {
	String getInfoFullName();
	String getContactEmail();
	LocalDate getInfoBirthDay();
}
