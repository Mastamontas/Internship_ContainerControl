package com.DEVLOP.Repositories;

import com.DEVLOP.Interfaces.IEquipmentServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EquipmentService {
    private final IEquipmentServiceRepo iEquipmentService;
    @Autowired
    public EquipmentService(IEquipmentServiceRepo iEquipmentService){
        this.iEquipmentService = iEquipmentService;
    }
}
