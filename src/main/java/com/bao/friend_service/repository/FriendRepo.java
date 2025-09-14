package com.bao.friend_service.repository;

import com.bao.friend_service.entity.Friend;
import com.bao.friend_service.repository.projection.BirthdayFriendProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FriendRepo extends JpaRepository<Friend, Long> {
	List<BirthdayFriendProjection> findByInfo_BirthDayBetween(LocalDate start, LocalDate end);

	@Query(value = """
	SELECT
		i.full_name AS infoFullName,
		c.email AS contactEmail,
		i.birth_day AS infoBirthday
	FROM friend f
	JOIN friend_info i ON i.id = f.info_id
	JOIN friend_contact c ON c.id = f.contact_id
	WHERE WEEK(DATE_ADD(i.birth_day, INTERVAL (YEAR(:today) - YEAR(i.birth_day)) YEAR)) = WEEK(:today)
	""", nativeQuery = true)
	List<BirthdayFriendProjection> findFriendsWithBirthdaysThisWeek(@Param("today") LocalDate today);
}
