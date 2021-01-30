package com.loanApplication.exception;

public class InvalidExpiredTokenException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpiredTokenException(String s)
	{
		super(s);
	}
		 
}