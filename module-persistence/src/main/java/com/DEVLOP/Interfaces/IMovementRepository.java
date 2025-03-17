package com.DEVLOP.Interfaces;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;

import java.util.List;
import java.util.Optional;

public interface IMovementRepository extends IGenericRepository<Movement, Integer>{
    List<Movement> findMovementsByEquipmentOrderByDateAsc(Equipment equipment);

    List<Movement> findMovementByEquipmentOrderByDateDesc(Equipment equipment);
}
