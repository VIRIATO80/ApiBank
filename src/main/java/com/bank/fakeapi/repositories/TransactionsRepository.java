package com.bank.fakeapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.fakeapi.beans.TransactionBean;

public interface TransactionsRepository extends JpaRepository<TransactionBean, String> {

}
