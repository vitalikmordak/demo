package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception will be thrown when user not found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoUserFoundException extends RuntimeException {
	public NoUserFoundException(long userId) {
		super(String.format("User with id %d not found.", userId));
	}
}
