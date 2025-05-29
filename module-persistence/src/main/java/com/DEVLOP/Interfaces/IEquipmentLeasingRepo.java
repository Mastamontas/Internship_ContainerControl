package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.EquipmentLeasing;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IEquipmentLeasingRepo extends IGenericRepository<EquipmentLeasing, Integer>, JpaSpecificationExecutor<EquipmentLeasing> {
    Optional<EquipmentLeasing> findByLeasingContractCode(String leasingContractCode);
}
