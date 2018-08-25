package com.capgemini.exception;

/**
 * This class include exception.
 *
 */
public class IncorrectParameterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * This method returns a message if an error occurs.
	 */
	public IncorrectParameterException(String message) {
		super("Incorrect parameter. " + message);
	}
}
