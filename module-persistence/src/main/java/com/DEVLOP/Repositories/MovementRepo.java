package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.IMovementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovementRepo {

    private final IMovementRepo iMovementRepo;

    @Autowired
    public MovementRepo(IMovementRepo iMovementRepo){
        this.iMovementRepo = iMovementRepo;
    }

    //tem de ser minusculo por ser metodo repositorio Spring
    public List<Movement> GetMovementsOfEquipment(Equipment eq){
        return iMovementRepo.findMovementByEquipmentOrderByDateDesc(eq);
    }

    public Optional<Movement> FindMovementById(int moveId){
        return iMovementRepo.findMovementById(moveId);
    }
    //must have associated equipment
    public Movement PersistMovement(Movement movement){
        iMovementRepo.save(movement);
        iMovementRepo.flush();
        return movement;
    }
    public String DeleteMovement(Movement movement){
        iMovementRepo.delete(movement);
        return "Movement has been deleted";
    }
    //todo: delete methods should change the isDeleted status to true
    public void DeleteAllMovements(){
        iMovementRepo.deleteAll();
        iMovementRepo.flush();
    }
    //refactor
    //todo find out why this is being used
    public List<Movement> FindAllMovements(){
        return iMovementRepo.findAll();
    }

    public Movement UpdateMovement(Movement mov){
        iMovementRepo.save(mov);
        iMovementRepo.flush();
        return mov;
    }
    //make this the general method for retrieving movement lists
    public List<Movement> ReturnFilteredMovementList(Specification<Movement> spec){
        return iMovementRepo.findAll(spec);
    }

    public String SaveMovementList(List<Movement> movementList){
        iMovementRepo.saveAll(movementList);
        return "Movement list has been saved";
    }
    public List<Movement> ReturnMovementsByIDList(List<Integer> ids){
        return iMovementRepo.findAllById(ids);
    }
    public List<Movement> ReturnMovementsWithEquipmentID(List<Integer> idList){
        return iMovementRepo.findAllByEquipment_IdIn(idList);
    }




}
