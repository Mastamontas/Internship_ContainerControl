package com.DEVLOP.Repositories;

import com.DEVLOP.DomainEntities.Equipment.Equipment;
import com.DEVLOP.Interfaces.IEquipmentRepository;
import com.DEVLOP.PersistenceEntities.EquipmentPersistenceEntity;
import com.DEVLOP.PersistenceMappers.IEquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EquipmentRepositoryImplementation implements IEquipmentRepository{
    private final IJpaEquipmentRepository iJpaEquipmentRepository;
    private final IEquipmentMapper iEquipmentMapper;

    public EquipmentRepositoryImplementation(IJpaEquipmentRepository iJpaEquipmentRepository, IEquipmentMapper iEquipmentMapper){
        this.iJpaEquipmentRepository = iJpaEquipmentRepository;
        this.iEquipmentMapper = iEquipmentMapper;
    }
    //aqui tem de retornar equipamento
    //a entidade jpa vai buscar o persistence entity, mas o mapper aqui dentro tem de converter em domain
    @Override
    public List<Equipment> findAll(){
        List<EquipmentPersistenceEntity> persistenceEntities = iJpaEquipmentRepository.findAll();
        return persistenceEntities.stream().map(iEquipmentMapper::toDomainEntity).collect(Collectors.toList());
    }
}

