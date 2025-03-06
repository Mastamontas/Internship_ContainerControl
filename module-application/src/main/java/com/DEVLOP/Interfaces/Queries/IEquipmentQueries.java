package com.DEVLOP.Interfaces.Queries;

import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Interfaces.IQueries;

import java.util.List;
import java.util.concurrent.CompletableFuture;
/*
todo
refactor nomes de metodos para pascal case

 */
public interface IEquipmentQueries extends IQueries<EquipmentDTO> {
    /*
    methods:
    FindAllEquipmentsAsync
    FindEquipmentByIDAsync
     */
    CompletableFuture<List<EquipmentDTO>> FindAllEquipmentsAsync();
    CompletableFuture<EquipmentDTO> GetEquipmentByIDAsync(int id);
}
