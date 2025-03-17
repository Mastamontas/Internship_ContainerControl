package com.DEVLOP.CustomExceptions.Movement;

public class MovementNotFoundException extends RuntimeException {
    public MovementNotFoundException(String message) {
        super(message);
    }
}
