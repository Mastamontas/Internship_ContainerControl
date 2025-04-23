package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IEquipmentCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentCondition {
    private final IEquipmentCondition iEquipmentCondition;
    @Autowired
    public EquipmentCondition(IEquipmentCondition iEquipmentCondition){
        this.iEquipmentCondition = iEquipmentCondition;
    }
}
