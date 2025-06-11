package com.DEVLOP.CustomExceptions;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException (String message){
        super(message);
    }
}
