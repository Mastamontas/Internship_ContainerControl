package com.DEVLOP.Interfaces.Queries;

import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Interfaces.IQueries;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IEquipmentQueries extends IQueries<EquipmentDTO> {
    /*
    methods:
    FindAllEquipmentsAsync
    FindEquipmentByIDAsync
     */
    CompletableFuture<List<EquipmentDTO>> findAllEquipmentsAsync();
}
