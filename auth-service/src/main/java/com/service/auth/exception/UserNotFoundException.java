package com.service.auth.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String errMsg){
        super(errMsg);
    }
}
