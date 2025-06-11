package com.DEVLOP.CustomExceptions;

public class EquipmentLeasingNotFoundException extends RuntimeException {
    public EquipmentLeasingNotFoundException(String message) {
        super(message);
    }
}
