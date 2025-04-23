package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IEquipmentStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentStatus {
    private final IEquipmentStatusRepo iEquipmentStatus;
    @Autowired
    public EquipmentStatus(IEquipmentStatusRepo iEquipmentStatus){
        this.iEquipmentStatus = iEquipmentStatus;
    }
}
