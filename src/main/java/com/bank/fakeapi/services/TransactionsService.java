package com.bank.fakeapi.services;

import java.util.List;

import com.bank.fakeapi.beans.SearchPayload;
import com.bank.fakeapi.beans.StatusPayload;
import com.bank.fakeapi.beans.TransactionBean;
import com.bank.fakeapi.exceptions.DuplicatedReferenceException;
import com.bank.fakeapi.exceptions.NotAccountExistsException;
import com.bank.fakeapi.exceptions.NotEnoughCreditException;

public interface TransactionsService {
	
	public TransactionBean createTransaction(TransactionBean newTransaction) throws NotAccountExistsException, NotEnoughCreditException, DuplicatedReferenceException;
	public TransactionBean getStatusTransaction(StatusPayload payload);
	public List<TransactionBean> findTransactions(SearchPayload criteria) throws NotAccountExistsException;
	
}
