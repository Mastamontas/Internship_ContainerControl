package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IMovementRepo extends IGenericRepository<Movement, Integer>, JpaSpecificationExecutor<Movement> {
    List<Movement> findMovementByEquipmentOrderByDateDesc(Equipment equipment);
    Optional<Movement> findMovementById(@NotNull int id);


    List<Movement> findAllByEquipment_IdIn(List<Integer> idList);

}