package com.service.auth.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String errMsg) {
        super(errMsg);
    }
}