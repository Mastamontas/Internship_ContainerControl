package com.DEVLOP.Interfaces;


import com.DEVLOP.Entities.MovementType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface IMovementTypeRepo extends IGenericRepository<MovementType, Integer>, JpaSpecificationExecutor<MovementType> {
    Optional<MovementType> findByMovementTypeCode(String movementTypeCode);
}
