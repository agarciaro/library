package com.imagina.ddd.application.exception;

import com.imagina.ddd.domain.model.user.UserId;

import lombok.Value;

@Value
public class UserNotFoundException extends RuntimeException {
	UserId userId;
}
