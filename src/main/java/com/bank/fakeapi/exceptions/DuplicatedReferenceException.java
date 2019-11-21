package com.bank.fakeapi.exceptions;

public class DuplicatedReferenceException extends Exception {

	public DuplicatedReferenceException() {
		super();
	}
	
	public DuplicatedReferenceException(String message) {
		super(message);
	}
}
