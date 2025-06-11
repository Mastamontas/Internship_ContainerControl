package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Application.Mappers.IEquipmentClassMapper;
import com.DEVLOP.CustomExceptions.EquipmentClassNotFoundException;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Repositories.EquipmentClassRepo;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentClassCommand {

    private final EquipmentClassRepo equipmentClassRepo;

    @Qualifier("IEquipmentClassMapperImpl")
    private final IEquipmentClassMapper mapper;

    public EquipmentClassCommand (EquipmentClassRepo equipmentClassRepo, @Qualifier("IEquipmentClassMapperImpl") IEquipmentClassMapper mapper){
        this.equipmentClassRepo = equipmentClassRepo;
        this.mapper = mapper;
    }


    @Transactional
    public CompletableFuture<EquipmentClassDto> CreateEquipmentClass (EquipmentClassDto equipmentClassDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentClass equipmentClass = mapper.MapToEquipmentClass(equipmentClassDto);
            equipmentClassRepo.PersistEquipmentClass(equipmentClass);
            return equipmentClassDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<EquipmentClassDto> UpdateEquipmentClass(EquipmentClassDto equipmentClassDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentClass equipmentClass = equipmentClassRepo.ReturnEquipmentClassByID(equipmentClassDto.getId())
                    .orElseThrow(()-> new EquipmentClassNotFoundException("Equipment class with that ID does not exist"));
            equipmentClassRepo.PersistEquipmentClass(mapper.UpdateEquipmentClass(equipmentClassDto,equipmentClass));
            return equipmentClassDto;
        }).exceptionally(ex->{
            throw new CompletionException(ex);
        });
    }
}
