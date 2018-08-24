package com.capgemini.exception;

public class MissingParameterException extends NullPointerException {
	private static final long serialVersionUID = 1L;

	public MissingParameterException(String message) {
		super(message);
	}

}
