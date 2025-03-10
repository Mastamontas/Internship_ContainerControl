package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.IMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovementRepository {
    private final IMovementRepository iMovementRepository;

    @Autowired
    public MovementRepository(IMovementRepository iMovementRepository){
        this.iMovementRepository = iMovementRepository;
    }

    public List<Movement> GetMovementsOfEquipment(Equipment eq){
        return iMovementRepository.findMovementsByEquipmentOrderByDateAsc(eq);
    }
}
