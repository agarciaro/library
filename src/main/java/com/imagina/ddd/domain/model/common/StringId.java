package com.imagina.ddd.domain.model.common;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class StringId extends EnityId<String>{

	public StringId(String value) {
		super(value);
		validateValue(value);
	}

	private void validateValue(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("The value cannot be null or empty");
		}
	}
	
}
