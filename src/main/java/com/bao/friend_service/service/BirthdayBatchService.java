package com.bao.friend_service.service;

import com.bao.friend_service.entity.Friend;
import com.bao.friend_service.repository.FriendRepo;
import com.bao.friend_service.repository.projection.BirthdayFriendProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayBatchService {
	private final FriendRepo friendRepo;

	public BirthdayBatchService(FriendRepo friendRepo) {
		this.friendRepo = friendRepo;
	}

	public List<BirthdayFriendProjection> getFriendsWithBirthdays(
			LocalDate start,
			LocalDate end) {
		return friendRepo.findByInfo_BirthDayBetween(start, end);
	}

	public List<BirthdayFriendProjection> getFriendsWithBirthdayThisWeek(
			LocalDate today) {
		return friendRepo.findFriendsWithBirthdaysThisWeek(today);
	}
}
