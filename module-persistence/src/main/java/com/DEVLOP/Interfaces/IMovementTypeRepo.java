package com.DEVLOP.Interfaces;

import com.DEVLOP.Repositories.EquipmentLeasing;
import com.DEVLOP.Repositories.MovementType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IMovementType extends IGenericRepository<MovementType, Integer>, JpaSpecificationExecutor<MovementType> {
}
