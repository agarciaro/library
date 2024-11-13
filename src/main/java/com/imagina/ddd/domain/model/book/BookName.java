package com.imagina.ddd.domain.model.book;

import com.imagina.ddd.domain.model.common.RequiredValue;

import lombok.NonNull;

public class BookName extends RequiredValue<String> {
	
	private BookName(@NonNull String value) {
		super(value);
		validate();
	}
	
	public static BookName of(@NonNull String value) {
		return new BookName(value);
	}

	private void validate() {
		if (getValue() == null || getValue().isBlank() || getValue().length() > 255) {
			throw new IllegalArgumentException("'Name' cannot be null or empty and between 1 and 255 chars");
		}
	}
	
}
