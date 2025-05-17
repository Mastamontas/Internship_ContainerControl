package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentLeasing;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IEquipmentLeasingRepo extends IGenericRepository<EquipmentLeasing, Integer>, JpaSpecificationExecutor<EquipmentLeasing> {
    EquipmentLeasing findByLeasingContractCode(String leasingContractCode);
}
