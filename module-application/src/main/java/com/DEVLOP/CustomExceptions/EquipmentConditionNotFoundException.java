package com.DEVLOP.CustomExceptions;

public class EquipmentConditionNotFoundException extends RuntimeException {
    public EquipmentConditionNotFoundException(String message) {
        super(message);
    }
}
