package com.bank.fakeapi.controllers;

import java.lang.reflect.Type;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.fakeapi.beans.SearchPayload;
import com.bank.fakeapi.beans.StatusPayload;
import com.bank.fakeapi.beans.TransactionBean;
import com.bank.fakeapi.exceptions.DuplicatedReferenceException;
import com.bank.fakeapi.exceptions.NotAccountExistsException;
import com.bank.fakeapi.exceptions.NotEnoughCreditException;
import com.bank.fakeapi.mappers.SearchMapper;
import com.bank.fakeapi.mappers.TransactionMapper;
import com.bank.fakeapi.services.TransactionsService;

@RestController
public class TransactionsController {

	@Autowired
	TransactionsService service;
	
    @Autowired
    private ModelMapper modelMapper;
	
	
	@PostMapping("/createTransaction")
	public ResponseEntity<Object> newTransaction(@Valid @RequestBody TransactionMapper transactionJSON) throws NotAccountExistsException, NotEnoughCreditException, DuplicatedReferenceException{
		TransactionBean transaction = modelMapper.map(transactionJSON , TransactionBean.class);
		String reference = service.createTransaction(transaction).getReference();
		return new ResponseEntity<Object>(null, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/search")
	public List<SearchMapper> searchTransactions(@RequestBody SearchPayload payload) throws NotAccountExistsException{			
		Type listType = new TypeToken<List<SearchMapper>>(){}.getType();
		return  modelMapper.map(service.findTransactions(payload) ,listType);
	}
	
	
	@PostMapping("/status")
	public ResponseEntity<TransactionMapper> getStatusTransaction(@Valid @RequestBody StatusPayload payload) {	
		return new ResponseEntity<TransactionMapper>(modelMapper.map(service.getStatusTransaction(payload), TransactionMapper.class), HttpStatus.OK);
	}
	

}
