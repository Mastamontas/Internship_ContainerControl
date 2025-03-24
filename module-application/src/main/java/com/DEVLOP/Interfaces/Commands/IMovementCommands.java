package com.DEVLOP.Interfaces.Commands;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Interfaces.ICommands;

import java.util.concurrent.CompletableFuture;

public interface IMovementCommands extends ICommands<Movement> {
    CompletableFuture<Movement> UpdateMovementAsync(int id, MovementDto movementDto);
}
