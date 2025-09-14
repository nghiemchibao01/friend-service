package com.bao.friend_service.batch;

import com.bao.friend_service.service.BirthdayBatchService;
import com.bao.friend_service.service.EmailService;
import com.bao.friend_service.service.FriendService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BirthdayBatch {
	private final BirthdayBatchService birthdayBatchService;
	private final EmailService emailService;

	public BirthdayBatch(
			BirthdayBatchService birthdayBatchService,
			EmailService emailService) {
		this.birthdayBatchService = birthdayBatchService;
		this.emailService = emailService;
	}

	@Scheduled(cron = "0 0 7 * * MON") // every Monday at 7:00 AM
	public void checkWeeklyBirthdays() {
		var today = LocalDate.now();

		var friends = birthdayBatchService.getFriendsWithBirthdayThisWeek(today);

		friends.forEach(friend -> {
			System.out.println("Send mail to: " + friend.getInfoFullName() + " <" + friend.getContactEmail() + ">");
		});
	}
}
