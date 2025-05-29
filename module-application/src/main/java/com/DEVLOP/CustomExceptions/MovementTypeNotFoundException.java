package com.DEVLOP.CustomExceptions;

public class MovementTypeNotFoundException extends RuntimeException {
    public MovementTypeNotFoundException(String message) {
        super(message);
    }
}
