package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentStatus;
import com.DEVLOP.Repositories.EquipmentStatusRepo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentStatusRepo extends IGenericRepository<EquipmentStatus, Integer>, JpaSpecificationExecutor<EquipmentStatus> {
}
