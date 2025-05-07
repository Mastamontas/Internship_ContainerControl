package com.DEVLOP.Interfaces;


import com.DEVLOP.Entities.MovementType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IMovementTypeRepo extends IGenericRepository<MovementType, Integer>, JpaSpecificationExecutor<MovementType> {
}
