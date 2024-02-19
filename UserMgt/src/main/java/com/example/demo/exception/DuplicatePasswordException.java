package com.example.demo.exception;

public class DuplicatePasswordException extends RuntimeException {
    public DuplicatePasswordException(String message) {
        super(message);
    }
}
