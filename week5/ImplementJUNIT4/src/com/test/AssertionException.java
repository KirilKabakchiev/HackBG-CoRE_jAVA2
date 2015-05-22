package com.test;

public class AssertionException extends RuntimeException {

	private static final String COOL_MESSAGE = "It is the cool exception message and shiet.. some default, apologies";

	public AssertionException() {
		super(COOL_MESSAGE);
	}

	public AssertionException(String message) {
		super(message);
	}

	public AssertionException(String message, Throwable cause) {
		super(message, cause);
	}

	public AssertionException(Throwable cause) {
		super(cause);
	}
}
