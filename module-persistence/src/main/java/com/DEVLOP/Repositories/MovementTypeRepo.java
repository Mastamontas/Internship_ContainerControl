package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IMovementType;
import org.springframework.beans.factory.annotation.Autowired;

public class MovementType {
    private IMovementType iMovementType;
    @Autowired
    public MovementType(IMovementType iMovementType){
        this.iMovementType = iMovementType;
    }
}
