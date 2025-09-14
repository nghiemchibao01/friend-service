package com.bao.friend_service.mapper;

import com.bao.friend_service.entity.*;
import com.bao.friend_service.model.*;

public class FriendMapper {
	public static FriendModel toModel(Friend entity) {
		if (entity == null) return null;
		FriendModel model = new FriendModel();
		model.setId(entity.getId());

		if (entity.getContact() != null) {
			ContactModel contactModel = getContactModel(entity);
			model.setContact(contactModel);
		}

		if (entity.getParent() != null) {
			ParentModel parentModel = getParentModel(entity);
			model.setParent(parentModel);
		}

		if (entity.getInfo() != null) {
			InfoModel infoModel = getInfoModel(entity);
			model.setInfo(infoModel);
		}
		return model;
	}

	private static ContactModel getContactModel(Friend entity) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(entity.getContact().getId());
		contactModel.setFb(entity.getContact().getFbUrl());
		contactModel.setIns(entity.getContact().getInsUrl());
		contactModel.setPhone(entity.getContact().getPhoneNum());
		contactModel.setEmail(entity.getContact().getEmail());
		return contactModel;
	}

	private static ParentModel getParentModel(Friend entity) {
		ParentModel parentModel = new ParentModel();
		parentModel.setId(entity.getParent().getId());
		parentModel.setFather(entity.getParent().getFatherName());
		parentModel.setMother(entity.getParent().getMotherName());
		return parentModel;
	}

	private static InfoModel getInfoModel(Friend entity) {
		InfoModel infoModel = new InfoModel();
		infoModel.setId(entity.getInfo().getId());
		infoModel.setFullName(entity.getInfo().getFullName());
		infoModel.setNickName(entity.getInfo().getNickName());
		infoModel.setBirthDay(entity.getInfo().getBirthDay());
		infoModel.setHobby(entity.getInfo().getHobby());
		infoModel.setAddress(entity.getInfo().getAddress());
		infoModel.setElementarySchool(entity.getInfo().getElementarySchool());
		infoModel.setMiddleSchool(entity.getInfo().getMiddleSchool());
		infoModel.setHighSchool(entity.getInfo().getHighSchool());
		infoModel.setUniversity(entity.getInfo().getUniversity());
		return infoModel;
	}

	public static Friend toEntity(FriendModel model) {
		if (model == null) return null;
		Friend entity = new Friend();
		entity.setId(model.getId());

		if (model.getContact() != null) {
			Contact contactEntity = getContactEntity(model);
			entity.setContact(contactEntity);
		}

		if (model.getParent() != null) {
			Parent parent = getParentEntity(model);
			entity.setParent(parent);
		}

		if (model.getInfo() != null) {
			Info info = getInfoEntity(model);
			entity.setInfo(info);
		}

		return entity;
	}

	private static Parent getParentEntity(FriendModel model) {
		Parent parent = new Parent();
		parent.setId(model.getParent().getId());
		parent.setFatherName(model.getParent().getFather());
		parent.setMotherName(model.getParent().getMother());
		return parent;
	}

	private static Contact getContactEntity(FriendModel model) {
		Contact contactEntity = new Contact();
		contactEntity.setId(model.getContact().getId());
		contactEntity.setFbUrl(model.getContact().getFb());
		contactEntity.setInsUrl(model.getContact().getIns());
		contactEntity.setPhoneNum(model.getContact().getPhone());
		contactEntity.setEmail(model.getContact().getEmail());
		return contactEntity;
	}

	private static Info getInfoEntity(FriendModel model) {
		Info infoEntity = new Info();
		infoEntity.setId(model.getInfo().getId());
		infoEntity.setFullName(model.getInfo().getFullName());
		infoEntity.setNickName(model.getInfo().getNickName());
		infoEntity.setBirthDay(model.getInfo().getBirthDay());
		infoEntity.setHobby(model.getInfo().getHobby());
		infoEntity.setAddress(model.getInfo().getAddress());
		infoEntity.setElementarySchool(model.getInfo().getElementarySchool());
		infoEntity.setMiddleSchool(model.getInfo().getMiddleSchool());
		infoEntity.setHighSchool(model.getInfo().getHighSchool());
		infoEntity.setUniversity(model.getInfo().getUniversity());
		return infoEntity;
	}
}
