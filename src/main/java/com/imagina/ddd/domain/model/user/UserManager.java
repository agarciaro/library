package com.imagina.ddd.domain.model.user;

import java.util.Optional;

public interface UserManager {
	
	void banUser(UserId userId);
	Optional<User> getUser(UserId userId);
	
}
