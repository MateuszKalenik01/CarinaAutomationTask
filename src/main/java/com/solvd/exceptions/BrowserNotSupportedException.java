package com.solvd.exceptions;


public class BrowserNotSupportedException extends RuntimeException {
    public BrowserNotSupportedException(String message) {
        super(message);
    }

    public BrowserNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}