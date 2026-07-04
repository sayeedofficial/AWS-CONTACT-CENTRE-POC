package com.sayeedofficial.contactcenter_api.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String message) {
        super(message);
    }
    
}
