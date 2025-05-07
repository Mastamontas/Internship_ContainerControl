package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentServiceRepo extends IGenericRepository<EquipmentService, Integer>, JpaSpecificationExecutor<EquipmentService> {
}
