package com.DEVLOP.Interfaces;

import com.DEVLOP.Repositories.EquipmentClass;
import com.DEVLOP.Repositories.EquipmentLeasing;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentLeasing extends IGenericRepository<EquipmentLeasing, Integer>, JpaSpecificationExecutor<EquipmentLeasing> {
}
