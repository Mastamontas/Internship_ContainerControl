package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentType extends IGenericRepository<EquipmentType, Integer>, JpaSpecificationExecutor<EquipmentType> {
}
