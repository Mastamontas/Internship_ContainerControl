package com.DEVLOP.CustomExceptions;

public class EquipmentUpdateException extends RuntimeException {
    public EquipmentUpdateException(String message) {
        super(message);
    }
    public EquipmentUpdateException(String message, Throwable cause){super(message, cause);}
}
