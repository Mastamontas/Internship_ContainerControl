package com.DEVLOP.Repositories;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.IEquipmentRepository;
import com.DEVLOP.PersistenceMappers.IEquipmentPersistenceMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Repository that creates, returns, updated and deletes equipment entities from the database
 * Implements the {@link IEquipmentRepository} from the domain module
 */
@Repository
public class EquipmentRepository implements IEquipmentRepository{
    private final IJpaEquipmentRepository iJpaEquipmentRepository;
    private final IEquipmentPersistenceMapper iEquipmentPersistenceMapper;

    public EquipmentRepository(IJpaEquipmentRepository iJpaEquipmentRepository, @Qualifier("IEquipmentMapperImpl") IEquipmentPersistenceMapper iEquipmentPersistenceMapper){
        this.iJpaEquipmentRepository = iJpaEquipmentRepository;
        this.iEquipmentPersistenceMapper = iEquipmentPersistenceMapper;
    }

    /**
     * Method to return all valid equipment entities from the database
     * @return list of equipment entities
     */
    @Override
    public List<Equipment> findAll(){
        return iJpaEquipmentRepository.findAll();
    }
}

