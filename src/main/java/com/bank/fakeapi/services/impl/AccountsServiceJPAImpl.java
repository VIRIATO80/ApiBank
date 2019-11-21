package com.bank.fakeapi.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.fakeapi.beans.AccountBean;
import com.bank.fakeapi.repositories.AccountsRepository;
import com.bank.fakeapi.services.AccountsService;

@Service
public class AccountsServiceJPAImpl implements AccountsService {

	@Autowired
	AccountsRepository repository;
	
	public AccountBean getAccount(String num_account) {
		Optional<AccountBean> account = repository.findById(num_account);
		if(account.isPresent())
			return account.get();
		else
			return null;
	}

	@Override
	public AccountBean createAccount(AccountBean account) {
		return repository.save(account);
	}

}
