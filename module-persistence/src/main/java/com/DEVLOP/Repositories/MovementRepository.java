package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.IMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovementRepository {

    private final IMovementRepository iMovementRepository;

    @Autowired
    public MovementRepository(IMovementRepository iMovementRepository){
        this.iMovementRepository = iMovementRepository;
    }

    //tem de ser minusculo por ser metodo repositorio Spring
    public List<Movement> GetMovementsOfEquipment(Equipment eq){
        return iMovementRepository.findMovementByEquipmentOrderByDateDesc(eq);
    }

    public Optional<Movement> FindMovementById(int moveId){
        return iMovementRepository.findMovementById(moveId);
    }
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
    //refactor
    //todo find out why this is being used
    public List<Movement> FindAllMovements(){
        return iMovementRepository.findAll();
    }

    public Movement UpdateMovement(Movement mov){
        iMovementRepository.save(mov);
        iMovementRepository.flush();
        return mov;
    }
    //make this the general method for retrieving movement lists
    public List<Movement> ReturnFilteredMovementList(Specification<Movement> spec){
        return iMovementRepository.findAll(spec);
    }

    public String SaveMovementList(List<Movement> movementList){
        iMovementRepository.saveAll(movementList);
        return "Movement list has been saved";
    }
    public List<Movement> ReturnMovementsByIDList(List<Integer> ids){
        return iMovementRepository.findAllById(ids);
    }




}
