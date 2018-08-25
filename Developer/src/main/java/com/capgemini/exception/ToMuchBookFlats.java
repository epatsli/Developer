package com.capgemini.exception;

/**
 * This class include exception when client have more than 3 book flats.
 *
 */
public class ToMuchBookFlats extends NullPointerException {

	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * This method returns a message when client have more than 3 book flats.
	 */
	public ToMuchBookFlats(String message) {
		this.message = message;
	}

}
