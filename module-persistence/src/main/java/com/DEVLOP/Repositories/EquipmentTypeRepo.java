package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Interfaces.IEquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentTypeRepo {
    private final IEquipmentTypeRepo iEquipmentTypeRepo;
    @Autowired
    public EquipmentTypeRepo(IEquipmentTypeRepo iEquipmentTypeRepo){
        this.iEquipmentTypeRepo = iEquipmentTypeRepo;
    }

    public EquipmentType PersistEquipmentType(EquipmentType eqType){
        return iEquipmentTypeRepo.save(eqType);
    }
    public Optional<EquipmentType> ReturnEquipmentTypeByID(int id){
        return iEquipmentTypeRepo.findById(id);
    }
    public List<EquipmentType> ReturnListOfEquipmentType(List<Integer> idList){
        return iEquipmentTypeRepo.findAllById(idList);
    }

}
