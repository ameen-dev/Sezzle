package com.ameen.calculator.exceptions;

public class EmptyInputException extends RuntimeException {
	public EmptyInputException(String s) {
		super(s);
	}

	private static final long serialVersionUID = 1L;
}
