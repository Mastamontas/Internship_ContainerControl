package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Application.Mappers.IMovementTypeMapper;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.MovementType;
import com.DEVLOP.Repositories.MovementTypeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
//falta implementar uma interface de commandos
@Service
public class MovementTypeCommand {
    private MovementTypeRepo movementTypeRepo;
    private IMovementTypeMapper mapper;

    @Autowired
    public MovementTypeCommand(MovementTypeRepo movementTypeRepo, @Qualifier("IMovementTypeMapperImpl") IMovementTypeMapper mapper){
        this.mapper = mapper;
        this.movementTypeRepo = movementTypeRepo;
    }

    @Transactional
    public CompletableFuture<MovementTypeDto> CreateMovementType(MovementTypeDto movementTypeDto) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(movementTypeDto.getMovementTypeCode());
            movementTypeRepo.PersistMovementType(mapper.MapToMovementType(movementTypeDto));
            return movementTypeDto;
        }).exceptionally(ex -> {
            throw new CompletionException(new MovementNotFoundException("Movement type not persisted"));
        });
    }

    @Transactional
    public CompletableFuture<MovementTypeDto> UpdateMovementType(MovementTypeDto movementTypeDto){
        return CompletableFuture.supplyAsync(()->{
            MovementType movementToUpdate = movementTypeRepo.FindMovementTypeByID(movementTypeDto.getId())
                    .orElseThrow(()-> new MovementNotFoundException("Movement type with that ID does not exist"));

            MovementType updatedMovement = mapper.UpdateMovementType(movementTypeDto, movementToUpdate);
            movementTypeRepo.PersistMovementType(updatedMovement);
            return movementTypeDto;
        }).exceptionally(ex->{
            throw new CompletionException(ex);
        });
    }

}
