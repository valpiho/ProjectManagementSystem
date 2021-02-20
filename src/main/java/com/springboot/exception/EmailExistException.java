package com.springboot.exception;

public class EmailExistException extends Exception{
    public EmailExistException(String message) {
        super(message);
    }
}
