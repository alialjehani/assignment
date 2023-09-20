package com.tree.assignment.exception;

public class SessionTimeoutException extends RuntimeException {
    public SessionTimeoutException(String message) {
        super(message);
    }
}
