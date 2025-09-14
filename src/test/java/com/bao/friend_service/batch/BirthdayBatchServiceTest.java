package com.bao.friend_service.batch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class BirthdayBatchServiceTest {
	@Autowired
	private BirthdayBatch birthdayBatch;

	@Test
	void testWeeklyBirthdays() {
		birthdayBatch.checkWeeklyBirthdays();
	}
}
