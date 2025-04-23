package com.DEVLOP.Interfaces;

import com.DEVLOP.Repositories.EquipmentService;
import com.DEVLOP.Repositories.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentStatus extends IGenericRepository<EquipmentStatus, Integer>, JpaSpecificationExecutor<EquipmentStatus> {
}
