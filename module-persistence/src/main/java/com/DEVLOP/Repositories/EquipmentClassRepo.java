package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IEquipmentClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentClass {
    private final IEquipmentClassRepo iEquipmentClass;
    @Autowired
    public EquipmentClass(IEquipmentClassRepo iEquipmentClass){
        this.iEquipmentClass = iEquipmentClass;
    }
}
