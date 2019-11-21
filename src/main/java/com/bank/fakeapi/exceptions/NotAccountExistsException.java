package com.bank.fakeapi.exceptions;

public class NotAccountExistsException extends Exception {

	public NotAccountExistsException() {
		super();
	}
	
	public NotAccountExistsException(String message) {
		super(message);
	}
}
