package com.bank.fakeapi.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "accounts")
public class AccountBean implements Serializable{
	
	@Id
	private String account_iban;
	
	private double total_balance=0;
	
	@OneToMany(mappedBy = "account_iban", fetch = FetchType.LAZY)
	private List<TransactionBean> transactions = new ArrayList<>();

	public AccountBean() {}
	
	
	public AccountBean(String account_iban, double total_balance) {
		super();
		this.account_iban = account_iban;
		this.total_balance = total_balance;
	}

	public double getTotal_balance() {
		return total_balance;
	}

	public void setTotal_balance(double total_balance) {
		this.total_balance = total_balance;
	}


	public String getAccount_iban() {
		return account_iban;
	}

	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}


	public List<TransactionBean> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<TransactionBean> transactions) {
		this.transactions = transactions;
	}

}
