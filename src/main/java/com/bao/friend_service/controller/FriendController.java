package com.bao.friend_service.controller;

import com.bao.friend_service.model.FriendModel;
import com.bao.friend_service.model.validation.OnCreate;
import com.bao.friend_service.model.validation.OnUpdate;
import com.bao.friend_service.service.FriendService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
	private final FriendService friendService;

	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}

	@PostMapping
	public FriendModel createFriend(
			@Validated(OnCreate.class) @RequestBody FriendModel friend) {
		return friendService.createOrUpdate(friend);
	}

	@PutMapping("/{id}")
	public FriendModel updateFriend(
			@PathVariable @NotNull Long id,
			@Validated(OnUpdate.class) @RequestBody FriendModel friend) {
		friend.setId(id); // enforce path variable id
		return friendService.createOrUpdate(friend);
	}


	@GetMapping
	public List<FriendModel> getAllFriends() {
		return friendService.getAllFriends();
	}

	@GetMapping("/{id}")
	public FriendModel getFriendById(@PathVariable @NotNull Long id) {
		return friendService.getFriendById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteFriend(@PathVariable @NotNull Long id) {
		friendService.deleteFriend(id);
	}

}
