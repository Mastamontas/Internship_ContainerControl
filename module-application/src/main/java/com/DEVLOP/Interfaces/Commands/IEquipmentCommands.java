package com.DEVLOP.Interfaces.Commands;

import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Interfaces.ICommands;

import java.util.concurrent.CompletableFuture;

public interface IEquipmentCommands extends ICommands<EquipmentDTO> {
    /*
    create async
    delete async
     */
    CompletableFuture<Void> UpdateEquipmentAsync(EquipmentDTO eqDTO);
}
