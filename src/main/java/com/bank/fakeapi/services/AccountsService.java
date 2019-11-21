package com.bank.fakeapi.services;

import com.bank.fakeapi.beans.AccountBean;

public interface AccountsService {
   
    public AccountBean createAccount(AccountBean account); 
	public AccountBean getAccount(String num_account);
}
