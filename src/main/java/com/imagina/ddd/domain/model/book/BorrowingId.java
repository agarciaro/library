package com.imagina.ddd.domain.model.book;

import java.util.UUID;

import com.imagina.ddd.domain.model.common.StringId;

public class BorrowingId extends StringId {
	
	private BorrowingId(UUID id) {
		super(id.toString());
	}

	public static BorrowingId generate() {
		return new BorrowingId(UUID.randomUUID());
	}

}
