package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
import com.DEVLOP.Application.Mappers.IEquipmentLeasingMapper;
import com.DEVLOP.CustomExceptions.EquipmentLeasingNotFoundException;
import com.DEVLOP.Entities.EquipmentLeasing;
import com.DEVLOP.Repositories.EquipmentLeasingRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentLeasingCommand {
    private final EquipmentLeasingRepo equipmentLeasingRepo;

    @Qualifier("IEquipementLeasingMapperImpl")
    private final IEquipmentLeasingMapper mapper;

    public EquipmentLeasingCommand(EquipmentLeasingRepo equipmentLeasingRepo, IEquipmentLeasingMapper mapper) {
        this.equipmentLeasingRepo = equipmentLeasingRepo;
        this.mapper = mapper;
    }
    @Transactional
    public CompletableFuture<EquipmentLeasingDto> CreateEquipmentLeasing(EquipmentLeasingDto equipmentLeasingDto) {
        return CompletableFuture.supplyAsync(() -> {
            EquipmentLeasing equipmentLeasing = mapper.MapToEquipmentLeasing(equipmentLeasingDto);
            equipmentLeasingRepo.PersistEquipmentLeasing(equipmentLeasing);
            return equipmentLeasingDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
    @Transactional
    public CompletableFuture<EquipmentLeasingDto> UpdateEquipmentLeasing(EquipmentLeasingDto equipmentLeasingDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentLeasing equipmentLeasing = equipmentLeasingRepo.ReturnEquipmentLeasingByID(equipmentLeasingDto.getId())
                    .orElseThrow(()-> new EquipmentLeasingNotFoundException("Equipment leasing with that ID does not exist"));
            equipmentLeasingRepo.PersistEquipmentLeasing(mapper.UpdateEquipmentLeasing(equipmentLeasingDto, equipmentLeasing));
            return equipmentLeasingDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

}
