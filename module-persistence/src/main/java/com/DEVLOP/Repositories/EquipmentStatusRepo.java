package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.EquipmentStatus;
import com.DEVLOP.Interfaces.IEquipmentStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public class EquipmentStatusRepo {
    private final IEquipmentStatusRepo iEquipmentStatusRepo;
    @Autowired
    public EquipmentStatusRepo(IEquipmentStatusRepo iEquipmentStatusRepo){
        this.iEquipmentStatusRepo = iEquipmentStatusRepo;
    }
    public EquipmentStatus PersistEquipmentStatus(EquipmentStatus eqStatus){
        return iEquipmentStatusRepo.save(eqStatus);
    }
    public Optional<EquipmentStatus> ReturnEquipmentStatusByID(int id){
        return iEquipmentStatusRepo.findById(id);
    }
    public List<EquipmentStatus> ReturnListOfEquipmentStatus(List<Integer> idList){
        return iEquipmentStatusRepo.findAllById(idList);
    }
}
