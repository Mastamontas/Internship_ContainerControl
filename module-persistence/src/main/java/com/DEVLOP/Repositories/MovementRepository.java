package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.IMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovementRepository {
    /*
    have to implement interfaces, not inject them
     */
    private final IMovementRepository iMovementRepository;

    @Autowired
    public MovementRepository(IMovementRepository iMovementRepository){
        this.iMovementRepository = iMovementRepository;
    }

    public List<Movement> GetMovementsOfEquipment(Equipment eq){
        return iMovementRepository.findMovementByEquipmentOrderByDateDesc(eq);//tem de ser minusculo por ser metodo repositorio Spring
    }
    /*
    return one
    update
     */
    //must have associated equipment
    public Movement PersistMovement(Movement movement){
        iMovementRepository.save(movement);
        iMovementRepository.flush();
        return movement;
    }
    public String DeleteMovement(Movement movement){
        iMovementRepository.delete(movement);
        return "Movement has been deleted";
    }
    public void DeleteAllMovements(){
        iMovementRepository.deleteAll();
        iMovementRepository.flush();
    }
    public List<Movement> FindAllMovements(){
        return iMovementRepository.findAll();
    }



}
