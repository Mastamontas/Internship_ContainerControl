package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface IMovementRepository extends IGenericRepository<Movement, Integer> {
    List<Movement> findMovementByEquipmentOrderByDateDesc(Equipment equipment);

    Optional<Movement> findMovementById(@NotNull int id);
}