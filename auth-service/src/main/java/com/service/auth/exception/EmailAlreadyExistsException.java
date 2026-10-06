package com.service.auth.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    
    public EmailAlreadyExistsException(String errMsg) {
        super(errMsg);
    }
}
