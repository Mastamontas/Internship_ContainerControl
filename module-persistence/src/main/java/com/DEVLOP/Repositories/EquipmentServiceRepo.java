package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Interfaces.IEquipmentServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentServiceRepo {
    private final IEquipmentServiceRepo iEquipmentServiceRepo;
    @Autowired
    public EquipmentServiceRepo(IEquipmentServiceRepo iEquipmentServiceRepo){
        this.iEquipmentServiceRepo = iEquipmentServiceRepo;
    }
    public EquipmentService PersistEquipmentService(EquipmentService eqService){
        return iEquipmentServiceRepo.save(eqService);
    }
    public Optional<EquipmentService> ReturnEquipmentServiceByID(int id){
        return iEquipmentServiceRepo.findById(id);
    }
    public List<EquipmentService> ReturnListOfEquipmentService(List<Integer> idList){
        return iEquipmentServiceRepo.findAllById(idList);
    }
}
