package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Application.Mappers.IMovementTypeMapper;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.CustomExceptions.MovementTypeNotFoundException;
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
import java.util.concurrent.CompletionException;

@Service
public class MovementTypeQuery {
    private final MovementTypeRepo movementTypeRepo;
    @Qualifier("IMovementTypeMapperImpl")
    private final IMovementTypeMapper mapper;

    @Autowired
    public MovementTypeQuery (MovementTypeRepo movementTypeRepo, @Qualifier("IMovementTypeMapperImpl") IMovementTypeMapper mapper){
        this.mapper = mapper;
        this.movementTypeRepo = movementTypeRepo;
    }
    @Transactional
    public CompletableFuture<MovementTypeDto> GetMovementTypeByIDAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            MovementType movementType = movementTypeRepo.FindMovementTypeByID(id)
                    .orElseThrow(() -> new MovementTypeNotFoundException("Movement type with that ID not found"));
            return mapper.MapToMovementTypeDto(movementType);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<MovementTypeDto> GetMovementTypeByCodeAsync(String code) {
        return CompletableFuture.supplyAsync(() -> {
            MovementType movementType = movementTypeRepo.ReturnMovementTypeByCode(code)
                    .orElseThrow(() -> new MovementTypeNotFoundException("Movement type with that code not found"));
            return mapper.MapToMovementTypeDto(movementType);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<List<MovementTypeDto>> GetMovementTypeListAsync(List<Integer> idList){
        return CompletableFuture.supplyAsync(()->{
            List<MovementType> movementTypeList = movementTypeRepo.ReturnListOfMovementTypes(idList);
            return movementTypeList.stream().map(mapper::MapToMovementTypeDto).toList();
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
}
