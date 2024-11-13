package com.imagina.ddd.domain.model.book;

import java.util.UUID;

import com.imagina.ddd.domain.model.common.StringId;

public class BookId extends StringId {
	
	private BookId(UUID uuid) {
		super(uuid.toString());
	}
	
	static BookId generate() {
		return new BookId(UUID.randomUUID());
	}

	static BookId to(UUID uuid) {
		return new BookId(uuid);
	}
	
}
