package com.DEVLOP.CustomExceptions;

public class TransportMeansNotFoundException extends RuntimeException {
    public TransportMeansNotFoundException(String message) {
        super(message);
    }
}
