package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentCondition;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentConditionRepo extends IGenericRepository<EquipmentCondition, Integer>, JpaSpecificationExecutor<EquipmentCondition> {
}
