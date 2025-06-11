package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.EquipmentStatusDto;
import com.DEVLOP.Application.Mappers.IEquipmentStatusMapper;
import com.DEVLOP.CustomExceptions.EquipmentStatusNotFoundException;
import com.DEVLOP.Entities.EquipmentStatus;
import com.DEVLOP.Repositories.EquipmentStatusRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentStatusCommand {
    private final EquipmentStatusRepo equipmentStatusRepo;

    @Qualifier("IEquipmentStatusImpl")
    private final IEquipmentStatusMapper mapper;

    public EquipmentStatusCommand (EquipmentStatusRepo equipmentStatusRepo, @Qualifier("IEquipmentStatusMapperImpl") IEquipmentStatusMapper mapper){
        this.equipmentStatusRepo = equipmentStatusRepo;
        this.mapper = mapper;
    }

    public CompletableFuture<EquipmentStatusDto> CreateEquipmentStatus(EquipmentStatusDto equipmentStatusDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentStatus equipmentStatus = mapper.MapToEquipmentStatus(equipmentStatusDto);
            equipmentStatusRepo.PersistEquipmentStatus(equipmentStatus);
            return equipmentStatusDto;
        }).exceptionally(ex->{
            throw new CompletionException(ex);
        });
    }

    public CompletableFuture<EquipmentStatusDto> UpdateEquipmentStatus(EquipmentStatusDto equipmentStatusDto){
        return CompletableFuture.supplyAsync(()->{

            EquipmentStatus equipmentStatus = equipmentStatusRepo.ReturnEquipmentStatusByID(equipmentStatusDto.getId())
                    .orElseThrow(()-> new EquipmentStatusNotFoundException("Equipment status with that ID does not exist"));
            equipmentStatusRepo.PersistEquipmentStatus(mapper.UpdateEquipmentStatus(equipmentStatusDto,equipmentStatus));
            return equipmentStatusDto;
        }).exceptionally(ex->{
            throw new CompletionException(ex);
        });
    }
}
