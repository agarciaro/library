package com.imagina.ddd.domain.model.user;

import lombok.Getter;

@Getter
public class User {
	
	private final UserId id;
	private final FullName fullName;
	private UserStatus status;

	public User(FullName fullName) {
		this.fullName = fullName;
		this.id = UserId.generate();
		this.status = UserStatus.ACTIVE;
	}

	public void ban() {
		if (status == UserStatus.BANNED) {
			throw new IllegalArgumentException("User " + id + " is already banned.");
		}
		this.status = UserStatus.BANNED;
	}
	
}
