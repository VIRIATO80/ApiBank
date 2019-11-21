package com.bank.fakeapi.exceptions;

public class NotEnoughCreditException extends Exception {

	public NotEnoughCreditException() {
		super();
	}
	
	public NotEnoughCreditException(String message) {
		super(message);
	}

}
