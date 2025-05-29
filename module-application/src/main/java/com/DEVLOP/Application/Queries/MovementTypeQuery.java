package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Application.Mappers.IMovementTypeMapper;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.MovementType;
import com.DEVLOP.Interfaces.Queries.IMovementQuery;
import com.DEVLOP.Repositories.MovementTypeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@Service
public class MovementTypeQuery {
    private MovementTypeRepo movementTypeRepo;
    private IMovementTypeMapper mapper;

    @Autowired
    public MovementTypeQuery (MovementTypeRepo movementTypeRepo, @Qualifier("IMovementTypeMapperImpl") IMovementTypeMapper mapper){
        this.mapper = mapper;
        this.movementTypeRepo = movementTypeRepo;
    }
    @Transactional
    public CompletableFuture<MovementTypeDto> GetMovementTypeByIDAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            MovementType movementType = movementTypeRepo.FindMovementTypeByID(id)
                    .orElseThrow(() -> new MovementNotFoundException("Movement type with that ID not found"));
            return mapper.MapToMovementTypeDto(movementType);
        });
    }

    @Transactional
    public CompletableFuture<MovementTypeDto> GetMovementTypeByCodeAsync(String code) {
        return CompletableFuture.supplyAsync(() -> {
            MovementType movementType = movementTypeRepo.ReturnMovementTypeByCode(code)
                    .orElseThrow(() -> new MovementNotFoundException("Movement type with that ID not found"));
            return mapper.MapToMovementTypeDto(movementType);
        });
    }

    @Transactional
    public CompletableFuture<List<MovementTypeDto>> GetMovementTypeListAsync(List<Integer> idList){
        return CompletableFuture.supplyAsync(()->{
            List<MovementType> movementTypeList = movementTypeRepo.ReturnListOfMovementTypes(idList);
            return movementTypeList.stream().map(mapper::MapToMovementTypeDto).toList();
        }).exceptionally(ex ->{
            throw new MovementNotFoundException("No movement list found with those id's");
        });
    }
}
