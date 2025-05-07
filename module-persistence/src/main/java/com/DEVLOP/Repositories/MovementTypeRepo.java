package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.MovementType;
import com.DEVLOP.Interfaces.IMovementTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovementTypeRepo {
    private IMovementTypeRepo iMovementTypeRepo;
    @Autowired
    public MovementTypeRepo(IMovementTypeRepo iMovementTypeRepo){
        this.iMovementTypeRepo = iMovementTypeRepo;
    }


    //this returns the entity because it can be used in the service layer, as returning it to the user after persisting it
    public MovementType PersistMovementType(MovementType movementType){
        return iMovementTypeRepo.saveAndFlush(movementType);
    }
    public Optional<MovementType> FindMovementTypeByID(int id){
        return iMovementTypeRepo.findById(id);
    }
    public List<MovementType> ReturnListOfMovementTypes(List<Integer> idList){
        return iMovementTypeRepo.findAllById(idList);
    }
    //delete entitied: turn isDeleted to false
}
