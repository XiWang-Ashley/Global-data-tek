package com.infosys.exception;

public class DuplicateUserException extends Exception{
    public DuplicateUserException(String message) {
        super(message);
    }
}
