package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Optional<Equipment> findEquipmentByUniqueDetails(@Param("prefix") String prefix, @Param("checkDigit")int checkDigit, @Param("number") int number){
        return iJpaEquipmentRepository.findByPrefixAndCheckDigitAndNumber(prefix,checkDigit, number);
    }

    public Equipment findByID(int id){
        return iJpaEquipmentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void updateEquipment(Equipment eq) {
        iJpaEquipmentRepository.saveAndFlush(eq);
    }
}

