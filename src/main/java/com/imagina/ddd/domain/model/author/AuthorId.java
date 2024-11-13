package com.imagina.ddd.domain.model.author;

import java.util.UUID;

import com.imagina.ddd.domain.model.common.StringId;

public class AuthorId extends StringId {
	
	private AuthorId(UUID uuid) {
		super(uuid.toString());
	}

	public static AuthorId generate() {
		return new AuthorId(UUID.randomUUID());
	}
	
}
