package com.kaishengit.exception;

public class DateAccessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DateAccessException() {}
	
	public DateAccessException(String message) {
		super(message);
	}
	
	public DateAccessException(Throwable th) {
		super(th);
	}
	
	public DateAccessException(String message,Throwable th) {
		super(message,th);
	}
	
}
