package com.bank.fakeapi.services.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import com.bank.fakeapi.beans.AccountBean;
import com.bank.fakeapi.beans.ChannelEnum;
import com.bank.fakeapi.beans.SearchPayload;
import com.bank.fakeapi.beans.StatusEnum;
import com.bank.fakeapi.beans.StatusPayload;
import com.bank.fakeapi.beans.TransactionBean;
import com.bank.fakeapi.exceptions.DuplicatedReferenceException;
import com.bank.fakeapi.exceptions.NotAccountExistsException;
import com.bank.fakeapi.exceptions.NotEnoughCreditException;
import com.bank.fakeapi.repositories.TransactionsRepository;
import com.bank.fakeapi.services.AccountsService;
import com.bank.fakeapi.services.TransactionsService;

@Service
public class TransactionsServiceJPAImpl implements TransactionsService {

	@Autowired
	private TransactionsRepository repositoryTrans;

	@Autowired
	private AccountsService accountService;	
	
	@Override
	public TransactionBean createTransaction(TransactionBean newTransaction) throws NotAccountExistsException, NotEnoughCreditException, DuplicatedReferenceException {
		if(repositoryTrans.existsById(newTransaction.getReference())){
			throw new DuplicatedReferenceException("Introduced reference already exists");
		}
		AccountBean account = accountService.getAccount(newTransaction.getAccount().getAccount_iban());		
		if(account == null) {//Transaction only can be a credit operation
			addCreditToAccountIfExists(newTransaction);
		}else {
			checkEnoughCredit(account, newTransaction);
		}
		return repositoryTrans.save(newTransaction);
	}

	
	private void addCreditToAccountIfExists(TransactionBean newTransaction) throws NotAccountExistsException {
		if(newTransaction.getAmount() > 0 && newTransaction.getAmount() > newTransaction.getFee()) {
			AccountBean newAccount = new AccountBean(newTransaction.getAccount().getAccount_iban(), newTransaction.getAmount() - newTransaction.getFee());
			accountService.createAccount(newAccount);
			newTransaction.setAccount(newAccount);		
		}else {
			throw new NotAccountExistsException("The account doesn´t exists");
		}		
	}
	
	
	private void checkEnoughCredit(AccountBean account, TransactionBean transaction) throws NotEnoughCreditException{		
		double totalBalanceAccount = account.getTotal_balance();	
		double totalTransaction = Math.abs(transaction.getAmount()) + transaction.getFee();
		if( transaction.getAmount() < 0 && 
			totalTransaction > totalBalanceAccount) {//Not enough money
			throw new NotEnoughCreditException("The account hasn´t credit enough");
		}else {
			double newBalance = (totalBalanceAccount + transaction.getAmount()) - transaction.getFee();
			account.setTotal_balance(newBalance);
			transaction.setAccount(account);
		}
	}

	@Override
	public List<TransactionBean> findTransactions(SearchPayload criteria) throws NotAccountExistsException{
		if(criteria.getAccount_iban() != null) {
			return searchByAccount(criteria);
		}else if(criteria.getSortCriteria() != null) {
			return searchTransactionsSorted(criteria);
		}
		return repositoryTrans.findAll();
	}


	private List<TransactionBean> searchByAccount(SearchPayload criteria) throws NotAccountExistsException {
		AccountBean account = accountService.getAccount(criteria.getAccount_iban());
		List<TransactionBean> transactions = null;
		if(account != null) {
			transactions = account.getTransactions();
			if(criteria.getSortCriteria() != null && criteria.getSortCriteria() .isAscending())	
				transactions.sort(Comparator.comparing(TransactionBean::getAmount));
			else if(criteria.getSortCriteria() != null && criteria.getSortCriteria() .isDescending())
				transactions.sort(Comparator.comparing(TransactionBean::getAmount).reversed());
			return transactions;
		}else {
			throw new NotAccountExistsException("The account doesn´t exists");
		}
	}

	private List<TransactionBean> searchTransactionsSorted(SearchPayload criteria) {
		Sort order = null;	
		if(criteria.getSortCriteria() .isAscending())			
			order = Sort.by(Direction.ASC, "amount");		
		else if(criteria.getSortCriteria().isDescending())
			order = Sort.by(Direction.DESC, "amount");		
		return repositoryTrans.findAll(order);
	}

	@Override
	public TransactionBean getStatusTransaction(StatusPayload payload) {
		if(payload.getReference() == null || payload.getReference().isEmpty())
			throw new HttpMessageNotReadableException("Reference is mandatory");
		Optional<TransactionBean> response = repositoryTrans.findById(payload.getReference());
        if (response.isPresent()) {
          return setStatus(response.get(), payload.getChannel());
        } 
		return new TransactionBean(payload.getReference(), StatusEnum.INVALID);
	}

	
	private TransactionBean setStatus(TransactionBean transaction, ChannelEnum channel) {
		setAmountByChannel(transaction, channel);
		setStatusByDate(transaction);
		if(channel.equals(ChannelEnum.ATM) && transaction.getStatus().equals(StatusEnum.FUTURE)) {
			transaction.setStatus(StatusEnum.PENDING);
		}
		return transaction;
	}

	
	private void setAmountByChannel(TransactionBean transaction, ChannelEnum channel) {	
		if(channel.equals(ChannelEnum.CLIENT) || channel.equals(ChannelEnum.ATM)) {
			transaction.setAmount(transaction.getAmount()-transaction.getFee());
			transaction.setFee(0d);
		}		
	}

	
	private void setStatusByDate(TransactionBean transaction) {	
		LocalDate dateOfTransaction = convertToLocalDate(transaction.getDate());		
		if(dateOfTransaction.isBefore(LocalDate.now())){
			transaction.setStatus(StatusEnum.SETTLED);
		}	
		if(dateOfTransaction.equals(LocalDate.now())) {
			transaction.setStatus(StatusEnum.PENDING);
		}		
		if(dateOfTransaction.isAfter(LocalDate.now())) {
			transaction.setStatus(StatusEnum.FUTURE);
		}			
	}
		
	
	private LocalDate convertToLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
}
