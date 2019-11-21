package com.bank.fakeapi.mappers;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SearchMapper {

	private String reference;

	private double amount;
	
	@JsonInclude(value = Include.NON_DEFAULT)
	private double fee;

	private String account_iban;
	
	@JsonInclude(value = Include.NON_DEFAULT)
	private String description="";
	
	private Date date;

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getAccount_iban() {
		return account_iban;
	}

	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
