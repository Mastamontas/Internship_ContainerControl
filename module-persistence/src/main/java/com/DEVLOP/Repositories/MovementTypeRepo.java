package com.DEVLOP.Repositories;

import com.DEVLOP.Entities.MovementType;
import com.DEVLOP.Interfaces.IMovementTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public class MovementTypeRepo {
    //podia extender esta classe e herdar os métodos, mas nao era possivel ter os nomes em letra maiuscula
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
    public Optional<MovementType> ReturnMovementTypeByCode(String code){
        return iMovementTypeRepo.findByMovementTypeCode(code);
    }

}
