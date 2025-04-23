package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IEquipmentLeasingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentLeasing {
    private final IEquipmentLeasingRepo iEquipmentLeasing;
    @Autowired
    public EquipmentLeasing(IEquipmentLeasingRepo iEquipmentLeasing){
        this.iEquipmentLeasing = iEquipmentLeasing;
    }
}
