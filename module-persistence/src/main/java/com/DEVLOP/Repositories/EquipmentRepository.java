package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EquipmentRepository  {
    private final IJpaEquipmentRepository iJpaEquipmentRepository;

    @Autowired
    public EquipmentRepository(IJpaEquipmentRepository iJpaEquipmentRepository){
        this.iJpaEquipmentRepository = iJpaEquipmentRepository;
    }

    /**
     * Method to return all valid equipment entities from the database
     * @return list of equipment entities
     */
    public List<Equipment> findAll(){
        return iJpaEquipmentRepository.findAll();
    }


    //retornar um equipamento por prefixo
    public Equipment findByPrefix(String prefix){
        return iJpaEquipmentRepository.findEquipmentByPrefix(prefix);
    }

    public Equipment findByID(int id){
        return iJpaEquipmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updateEquipment(Equipment eq) {
        //nao tem de fazer esta verificação aqui, faz na application
        iJpaEquipmentRepository.saveAndFlush(eq);
    }
}

