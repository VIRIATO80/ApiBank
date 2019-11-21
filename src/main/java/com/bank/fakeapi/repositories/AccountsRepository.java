package com.bank.fakeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.fakeapi.beans.AccountBean;

public interface AccountsRepository extends JpaRepository<AccountBean, String>{

}
