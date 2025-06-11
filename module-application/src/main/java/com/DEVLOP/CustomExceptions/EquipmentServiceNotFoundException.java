package com.DEVLOP.CustomExceptions;

public class EquipmentServiceNotFoundException extends RuntimeException {
    public EquipmentServiceNotFoundException(String message) {
        super(message);
    }
}
