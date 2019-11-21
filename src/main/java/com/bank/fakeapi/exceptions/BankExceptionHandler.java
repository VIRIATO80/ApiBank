package com.bank.fakeapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class BankExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class, 
    							HttpMessageNotReadableException.class,
    							NotEnoughCreditException.class,
    							NotAccountExistsException.class,
    							DuplicatedReferenceException.class
    						   })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiErrorResponse handleNotAccountExistsException(Exception ex, WebRequest req) {
		return new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }
	
	
    
}
