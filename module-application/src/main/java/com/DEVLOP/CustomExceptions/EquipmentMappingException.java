package com.DEVLOP.CustomExceptions;

public class EquipmentMappingException extends RuntimeException {
    public EquipmentMappingException(String message) {
        super(message);
    }
    public EquipmentMappingException(String message, Throwable cause){super(message, cause);}
}
