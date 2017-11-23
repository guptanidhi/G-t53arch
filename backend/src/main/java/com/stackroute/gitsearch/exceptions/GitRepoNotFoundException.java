package com.stackroute.gitsearch.exceptions;

@SuppressWarnings("serial")
public class GitRepoNotFoundException extends Exception {

	public GitRepoNotFoundException(String message) {
		super(message);
	}
}
