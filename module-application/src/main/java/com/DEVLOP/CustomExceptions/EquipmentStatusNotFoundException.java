package com.DEVLOP.CustomExceptions;

public class EquipmentStatusNotFoundException extends RuntimeException {
    public EquipmentStatusNotFoundException(String message) {
        super(message);
    }
}
