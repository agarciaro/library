package com.imagina.ddd.domain.model.book;

import com.imagina.ddd.domain.model.common.RequiredValue;

import lombok.NonNull;

public class ISBN extends RequiredValue<String> {
	
	private ISBN(@NonNull String value) {
		super(value);
		validate();
	}
	
	public static ISBN of(@NonNull String value) {
		return new ISBN(value);
	}

	private void validate() {
		if (getValue() == null || getValue().isBlank() || getValue().length() != 13) {
			throw new IllegalArgumentException("'ISBN' cannot be null or empty and exactly 13 chars");
		}
	}

}
