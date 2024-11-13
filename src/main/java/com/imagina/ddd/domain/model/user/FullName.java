package com.imagina.ddd.domain.model.user;

import com.imagina.ddd.domain.model.common.RequiredValue;

import lombok.NonNull;

public class FullName extends RequiredValue<String>{
	
	private FullName(@NonNull String name) {
		super(name);
		validate();
	}

	public static FullName of(@NonNull String name) {
		return new FullName(name);
	}

	private void validate() {
		if (getValue().length() < 3) {
			throw new IllegalArgumentException("Full name must have at least 3 characters");
		}
	}
	
}
