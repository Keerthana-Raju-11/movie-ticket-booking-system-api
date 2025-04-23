package com.example.mdb.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);  // Pass the message to the superclass constructor
    }
}
