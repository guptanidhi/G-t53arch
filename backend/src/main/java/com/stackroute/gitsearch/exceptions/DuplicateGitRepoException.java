package com.stackroute.gitsearch.exceptions;

@SuppressWarnings("serial")
public class DuplicateGitRepoException extends Exception {

	public DuplicateGitRepoException(String message) {
		super(message);
	}
}
