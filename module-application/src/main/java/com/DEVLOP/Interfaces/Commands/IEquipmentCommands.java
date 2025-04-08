package com.DEVLOP.Interfaces.Commands;

import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.ICommands;

import java.util.concurrent.CompletableFuture;
public interface IEquipmentCommands extends ICommands<Equipment> {
    CompletableFuture<Equipment> UpdateEquipment(int id, EquipmentDto eqDTO);
}
