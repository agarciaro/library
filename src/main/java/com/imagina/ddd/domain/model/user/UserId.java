package com.imagina.ddd.domain.model.user;

import java.util.UUID;

import com.imagina.ddd.domain.model.common.StringId;

public class UserId extends StringId {
	
	private UserId(UUID id) {
		super(id.toString());
	}

	public static UserId generate() {
		return new UserId(UUID.randomUUID());
	}
	
}
