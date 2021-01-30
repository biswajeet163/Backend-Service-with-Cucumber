package com.loanApplication.exception;

public class LoanDoesNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoanDoesNotExistException(String s)
	{
		super(s);
	}
}
