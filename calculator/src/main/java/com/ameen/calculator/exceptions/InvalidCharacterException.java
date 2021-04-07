package com.ameen.calculator.exceptions;

public class InvalidCharacterException extends RuntimeException {
	public InvalidCharacterException(String s) {
		super(s);
	}

	private static final long serialVersionUID = 1L;
}
