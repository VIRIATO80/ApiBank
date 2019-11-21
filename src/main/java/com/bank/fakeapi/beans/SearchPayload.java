package com.bank.fakeapi.beans;

import org.springframework.data.domain.Sort.Direction;

public class SearchPayload {

	private String account_iban;
	
	private Direction sortCriteria;
	

	public String getAccount_iban() {
		return account_iban;
	}

	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}

	public Direction getSortCriteria() {
		return sortCriteria;
	}

	public void setSortCriteria(Direction sortCriteria) {
		this.sortCriteria = sortCriteria;
	}
	
	
}
