package com.bank.fakeapi.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "transactions")
public class TransactionBean implements Serializable{

	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="com.bank.fakeapi.generators.ManualIdOrGenerate")
	private String reference;
	

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_iban", nullable = false)
	private AccountBean account_iban;
	
	private Date date= new Date();
	
	private double amount;
	
	private double fee;

	private String description="";
	
	@Transient
	private StatusEnum status;
	
	
	public TransactionBean() {}

	
	public TransactionBean(String reference, StatusEnum status) {
		this.reference = reference;
		this.status = status;
	}

	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
		
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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

	public AccountBean getAccount() {
		return account_iban;
	}

	public void setAccount(AccountBean account) {
		this.account_iban = account;
	}

}
