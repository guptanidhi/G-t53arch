package com.stackroute.gitsearch.exceptions;

@SuppressWarnings("serial")
public class DuplicateUserFoundException extends Exception {

	public DuplicateUserFoundException(String message) {
		super(message);
	}
}
