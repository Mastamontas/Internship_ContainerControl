package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.Commands.IMovementCommands;
import com.DEVLOP.Repositories.MovementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
            return mapper.ConvertMovementDtoToEntity(movementDto, move);
        }).thenApplyAsync(updatedMovement ->{
            movementRepository.UpdateMovement(updatedMovement);
            return updatedMovement;
        });
    }
}
