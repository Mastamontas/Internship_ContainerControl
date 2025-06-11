package com.DEVLOP.CustomExceptions;

public class EquipmentTypeNotFoundException extends RuntimeException {
    public EquipmentTypeNotFoundException(String message) {
        super(message);
    }
}
