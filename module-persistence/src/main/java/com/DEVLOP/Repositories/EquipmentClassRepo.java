package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Interfaces.IEquipmentClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentClassRepo {
    private final IEquipmentClassRepo iEquipmentClassRepo;
    @Autowired
    public EquipmentClassRepo(IEquipmentClassRepo iEquipmentClassRepo){
        this.iEquipmentClassRepo = iEquipmentClassRepo;
    }
    public EquipmentClass PersistEquipmentClass(EquipmentClass eqClass){
        return iEquipmentClassRepo.save(eqClass);
    }
    public Optional<EquipmentClass> ReturnEquipmentClassByID(int id){
        return iEquipmentClassRepo.findById(id);
    }
    public List<EquipmentClass> ReturnEquipmentClassListByID(List<Integer> idList){
        return iEquipmentClassRepo.findAllById(idList);
    }
}
