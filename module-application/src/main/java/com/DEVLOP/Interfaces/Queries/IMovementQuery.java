package com.DEVLOP.Interfaces.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Interfaces.IQueries;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IMovementQuery extends IQueries<MovementDto> {
    CompletableFuture<List<MovementDto>> ReturnMovementListFromEquipAsync(int id);
    CompletableFuture<MovementDto> ReturnMovementById(int movementId);
    CompletableFuture<List<MovementDto>> ReturnFilteredMovementListAsync(Map<String,Object> filters);
}
