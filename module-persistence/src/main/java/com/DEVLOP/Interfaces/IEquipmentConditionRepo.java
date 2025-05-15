package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentCondition;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IEquipmentConditionRepo extends IGenericRepository<EquipmentCondition, Integer>, JpaSpecificationExecutor<EquipmentCondition> {
    Optional<EquipmentCondition> findByPhysicalConditionCode(String physicalConditionCode);
}
