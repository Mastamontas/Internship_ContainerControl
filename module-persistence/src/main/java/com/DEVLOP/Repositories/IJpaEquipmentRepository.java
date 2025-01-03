package com.DEVLOP.Repositories;

import com.DEVLOP.DomainEntities.Equipment.Equipment;
import com.DEVLOP.PersistenceEntities.EquipmentPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IJpaEquipmentRepository extends JpaRepository<EquipmentPersistenceEntity, Integer> {
    @Override
    List<EquipmentPersistenceEntity> findAll();
}
