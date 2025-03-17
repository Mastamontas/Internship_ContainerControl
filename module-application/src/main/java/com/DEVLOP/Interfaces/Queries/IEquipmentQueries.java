package com.DEVLOP.Interfaces.Queries;

import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Interfaces.IQueries;

import java.util.List;
import java.util.concurrent.CompletableFuture;
/*
todo
refactor nomes de metodos para pascal case

 */
public interface IEquipmentQueries extends IQueries<EquipmentDto> {
    CompletableFuture<List<EquipmentDto>> FindAllEquipmentsAsync();
    CompletableFuture<EquipmentDto> GetEquipmentByIDAsync(int id);
}
