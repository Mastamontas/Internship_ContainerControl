package com.DEVLOP.Interfaces;

import com.DEVLOP.Repositories.EquipmentCondition;
import com.DEVLOP.Repositories.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentCondition extends IGenericRepository<EquipmentCondition, Integer>, JpaSpecificationExecutor<EquipmentCondition> {
}
