package com.bank.fakeapi.mappers;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.bank.fakeapi.beans.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class TransactionMapper {


	private String reference;

	private StatusEnum status;
	
	@NotNull(message = "The amount is mandatory")
	private double amount;
	
	@JsonInclude(value = Include.NON_DEFAULT)
	private double fee;

	@NotNull(message = "The account_iban is mandatory")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String account_iban;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String description="";
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Date date= new Date();
	

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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
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
