package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IEquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentType {
    private final IEquipmentTypeRepo iEquipmentType;
    @Autowired
    public EquipmentType(IEquipmentTypeRepo iEquipmentType){
        this.iEquipmentType = iEquipmentType;
    }
    /*
    save
    delete
    update
     */

}
