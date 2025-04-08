package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Commands.IMovementCommands;
import com.DEVLOP.Repositories.MovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class MovementCommand implements IMovementCommands {

    private final IMovementMapper mapper;
    private final MovementRepository movementRepository;


    @Autowired
    public MovementCommand(@Qualifier("IMovementMapperImpl") IMovementMapper mapper, MovementRepository movementRepository){
        this.mapper = mapper;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public CompletableFuture<Movement> UpdateMovementAsync(int id, MovementDto movementDto){
        return CompletableFuture.supplyAsync(()->{
            Movement move = movementRepository.FindMovementById(id).orElseThrow(()-> new MovementNotFoundException("Movement with that id " +
                    "does not exist"));
            return mapper.UpdateMovementEntity(movementDto, move);
        }).thenApplyAsync(updatedMovement ->{
            movementRepository.UpdateMovement(updatedMovement);
            return updatedMovement;
        });
    }
    @Transactional
    public CompletableFuture<List<MovementDto>> ChangeGroupMovement(List<Integer> movementIDs, MovementDto movementToChange){
        return CompletableFuture.supplyAsync(()-> {
            List<Movement> movementList = GetSelectedMovements(movementIDs);
            return movementList.stream()
                    .map(mov -> mapper.UpdateMovementEntity(movementToChange, mov))
                    .toList();
        }).thenApply(updatedMovements->{
            movementRepository.SaveMovementList(updatedMovements);
            return updatedMovements.stream().map(mapper::MapToMovementDto).toList();
        }).exceptionally(e ->{
            throw new MovementNotFoundException("Something went wrong in changing the group movements");
        });
    };
    private List<Movement> GetSelectedMovements(List<Integer> ids){
        List<Movement> movements = movementRepository.ReturnMovementsByIDList(ids);
        if (movements.isEmpty() || movements.size() != ids.size()) {
            throw new MovementNotFoundException("One or more movements not found for the provided IDs: " + ids);
        }
        return movements;
    }

    //todo add group movement
    /*
    para uma lista de equipamentos, vao se criar o mesmo numero de movimentos, mesmo que seja iguais entre si, variando o prefixo do equipamento
    for each equipment in the list, add that equipment to the movement
    persist the movement that has different equipments-

    get a list of ID's
    return the equipments associated to those ID's
    get the movement DTO with the details regarding the movement, not the details of the equipments
    ou seja, sao precisos 3 contentores para fazer o mesmo movimento mas em datas diferentes, pode se adicionar o movimento de grupo
     */
}
