package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IEquipmentServiceRepo extends IGenericRepository<EquipmentService, Integer>, JpaSpecificationExecutor<EquipmentService> {
    Optional<EquipmentService> findByEquipmentServiceCode(String equipmentServiceCode);
}
