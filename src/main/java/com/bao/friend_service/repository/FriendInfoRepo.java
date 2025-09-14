package com.bao.friend_service.repository;

import com.bao.friend_service.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendInfoRepo extends JpaRepository<Info, Long> {
}
