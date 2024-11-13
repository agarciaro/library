package com.imagina.ddd.domain.model.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class EnityId<T> {
	@NonNull
	private T value;

	@Override
	public String toString() {
		return "EnityId [value=" + value + "]";
	}
	
}
