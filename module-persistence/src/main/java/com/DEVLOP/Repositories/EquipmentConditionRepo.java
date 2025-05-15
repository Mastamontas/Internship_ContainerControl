package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.EquipmentCondition;
import com.DEVLOP.Interfaces.IEquipmentConditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentConditionRepo {
    private final IEquipmentConditionRepo iEquipmentConditionRepo;
    @Autowired
    public EquipmentConditionRepo(IEquipmentConditionRepo iEquipmentConditionRepo){
        this.iEquipmentConditionRepo = iEquipmentConditionRepo;
    }
    public EquipmentCondition PersistEquipmentCondition(EquipmentCondition eqCondition){
        return iEquipmentConditionRepo.saveAndFlush(eqCondition);

    }
    public Optional<EquipmentCondition> FindEquipmentConditionByID(int id){
        return iEquipmentConditionRepo.findById(id);
    }
    public List<EquipmentCondition> ReturnListOfEquipmentCondition(List<Integer> idList){
        return iEquipmentConditionRepo.findAllById(idList);
    }
    public Optional<EquipmentCondition> FindEquipmentConditionByCode(String code){
        return iEquipmentConditionRepo.findByPhysicalConditionCode(code);
    }
}
