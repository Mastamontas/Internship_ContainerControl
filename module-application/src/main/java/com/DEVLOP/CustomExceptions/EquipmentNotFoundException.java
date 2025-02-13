package com.DEVLOP.CustomExceptions;

import com.DEVLOP.Entities.Equipment;
import org.hibernate.ObjectNotFoundException;

import java.util.List;

public class EquipmentNotFoundException extends RuntimeException {
    public EquipmentNotFoundException (String message){
        super(message);
    }
}
