package com.DEVLOP.Application.Queries;

import com.DEVLOP.Application.DTOS.EquipmentStatusDto;
import com.DEVLOP.Application.Mappers.IEquipmentStatusMapper;
import com.DEVLOP.Entities.EquipmentStatus;
import com.DEVLOP.Repositories.EquipmentStatusRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentStatusQuery {
    private final EquipmentStatusRepo equipmentStatusRepo;
    @Qualifier("IEquipmentStatusImpl")
    private final IEquipmentStatusMapper mapper;

    public EquipmentStatusQuery(EquipmentStatusRepo equipmentStatusRepo, IEquipmentStatusMapper mapper){
        this.equipmentStatusRepo = equipmentStatusRepo;
        this.mapper = mapper;
    }
    public CompletableFuture<EquipmentStatusDto> GetEquipmentStatusByID (int id){
        return CompletableFuture.supplyAsync(()->{
            EquipmentStatus equipmentStatus = equipmentStatusRepo.ReturnEquipmentStatusByID(id).orElseThrow();
            return mapper.MapToEquipmentStatusDto(equipmentStatus);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    public CompletableFuture<EquipmentStatusDto> GetEquipmentStatusByCode (String code){
        return CompletableFuture.supplyAsync(()->{
            EquipmentStatus equipmentStatus = equipmentStatusRepo.ReturnEquipmentStatusByCode(code).orElseThrow();
            return mapper.MapToEquipmentStatusDto(equipmentStatus);
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
}
