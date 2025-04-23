package com.DEVLOP.Interfaces;

import com.DEVLOP.Repositories.EquipmentService;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentService extends IGenericRepository<EquipmentService, Integer>, JpaSpecificationExecutor<EquipmentService> {
}
