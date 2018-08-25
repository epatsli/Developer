package com.capgemini.exception;

public class ToMuchBookFlats extends NullPointerException {

	private static final long serialVersionUID = 1L;
	private String message;

	public ToMuchBookFlats(String message) {
		this.message = message;
	}

}
