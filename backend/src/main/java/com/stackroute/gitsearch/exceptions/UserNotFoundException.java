package com.stackroute.gitsearch.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

	public UserNotFoundException(String message) {
		super(message);
	}
}
