package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.IEquipmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository that creates, returns, updated and deletes equipment entities from the database
 * Implements the {@link IEquipmentRepository} from the domain module
 */
@Repository
public class
EquipmentRepository implements IEquipmentRepository{
    private final IJpaEquipmentRepository iJpaEquipmentRepository;

    public EquipmentRepository(IJpaEquipmentRepository iJpaEquipmentRepository){
        this.iJpaEquipmentRepository = iJpaEquipmentRepository;
    }

    /**
     * Method to return all valid equipment entities from the database
     * @return list of equipment entities
     */
    @Override
    public List<Equipment> findAll(){
        return iJpaEquipmentRepository.findAll();
    }


    //retornar um equipamento por prefixo
    @Override
    public Equipment findByPrefix(String prefix){
        return iJpaEquipmentRepository.findEquipmentByPrefix(prefix);
    }

    @Override
    public Equipment findByID(int id){
        return iJpaEquipmentRepository.findEquipmentById(id);
    }

    @Transactional
    @Override
    public void updateEquipment(Equipment eq) {
        //nao tem de fazer esta verificação aqui, faz na application
        iJpaEquipmentRepository.saveAndFlush(eq);
    }
}

