package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.IMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class Movement {

    private final IMovement iMovement;

    @Autowired
    public Movement(IMovement iMovement){
        this.iMovement = iMovement;
    }

    //tem de ser minusculo por ser metodo repositorio Spring
    public List<com.DEVLOP.Entities.Movement> GetMovementsOfEquipment(Equipment eq){
        return iMovement.findMovementByEquipmentOrderByDateDesc(eq);
    }

    public Optional<com.DEVLOP.Entities.Movement> FindMovementById(int moveId){
        return iMovement.findMovementById(moveId);
    }
    //must have associated equipment
    public com.DEVLOP.Entities.Movement PersistMovement(com.DEVLOP.Entities.Movement movement){
        iMovement.save(movement);
        iMovement.flush();
        return movement;
    }
    public String DeleteMovement(com.DEVLOP.Entities.Movement movement){
        iMovement.delete(movement);
        return "Movement has been deleted";
    }
    public void DeleteAllMovements(){
        iMovement.deleteAll();
        iMovement.flush();
    }
    //refactor
    //todo find out why this is being used
    public List<com.DEVLOP.Entities.Movement> FindAllMovements(){
        return iMovement.findAll();
    }

    public com.DEVLOP.Entities.Movement UpdateMovement(com.DEVLOP.Entities.Movement mov){
        iMovement.save(mov);
        iMovement.flush();
        return mov;
    }
    //make this the general method for retrieving movement lists
    public List<com.DEVLOP.Entities.Movement> ReturnFilteredMovementList(Specification<com.DEVLOP.Entities.Movement> spec){
        return iMovement.findAll(spec);
    }

    public String SaveMovementList(List<com.DEVLOP.Entities.Movement> movementList){
        iMovement.saveAll(movementList);
        return "Movement list has been saved";
    }
    public List<com.DEVLOP.Entities.Movement> ReturnMovementsByIDList(List<Integer> ids){
        return iMovement.findAllById(ids);
    }




}
