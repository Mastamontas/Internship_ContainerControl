package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.EquipmentLeasing;
import com.DEVLOP.Interfaces.IEquipmentLeasingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentLeasingRepo {
    private final IEquipmentLeasingRepo iEquipmentLeasingRepo;
    @Autowired
    public EquipmentLeasingRepo(IEquipmentLeasingRepo iEquipmentLeasingRepo){
        this.iEquipmentLeasingRepo = iEquipmentLeasingRepo;
    }
    public EquipmentLeasing PersistEquipmentLeasing(EquipmentLeasing eqLeasing){
        return iEquipmentLeasingRepo.saveAndFlush(eqLeasing);
    }
    public Optional<EquipmentLeasing> ReturnEquipmentLeasingByID(int id){
        return iEquipmentLeasingRepo.findById(id);
    }
    public List<EquipmentLeasing> ReturnListOfEquipmentLeasing(List<Integer> idList){
        return iEquipmentLeasingRepo.findAllById(idList);
    }

    public EquipmentLeasing ReturnEquipmentLeasingByCode(String code) {
        return iEquipmentLeasingRepo.findByLeasingContractCode(code);
    }

    public List<EquipmentLeasing> FindAll() {
        return iEquipmentLeasingRepo.findAll();
    }
}
