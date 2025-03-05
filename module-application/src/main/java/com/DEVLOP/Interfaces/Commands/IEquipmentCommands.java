package com.DEVLOP.Interfaces.Commands;

import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.ICommands;

import java.util.concurrent.CompletableFuture;
/*
todo
methods in commands are not async
 */
public interface IEquipmentCommands extends ICommands<Equipment> {
    /*
    create async
    delete async
     */
    CompletableFuture<Equipment> UpdateEquipment(int id, EquipmentDTO eqDTO);
}
