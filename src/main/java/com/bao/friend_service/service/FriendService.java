package com.bao.friend_service.service;

import com.bao.friend_service.entity.Friend;
import com.bao.friend_service.exception.ResourceNotFoundException;
import com.bao.friend_service.model.FriendModel;
import com.bao.friend_service.mapper.FriendMapper;
import com.bao.friend_service.repository.FriendRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
	@Autowired
	private FriendRepo friendRepo;

	public FriendModel createOrUpdate(FriendModel friendModel) {
		Friend entity = FriendMapper.toEntity(friendModel);
		Friend saved = friendRepo.save(entity);
		return FriendMapper.toModel(saved);
	}

	public List<FriendModel> getAllFriends() {
		return friendRepo.findAll()
				.stream()
				.map(FriendMapper::toModel)
				.toList();
	}

	public FriendModel getFriendById(Long id) {
		var entity = friendRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Friend", id));

		return FriendMapper.toModel(entity);
	}

	public void deleteFriend(Long id) {
		friendRepo.deleteById(id);
	}
}
