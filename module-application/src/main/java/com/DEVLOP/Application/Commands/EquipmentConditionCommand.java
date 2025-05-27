package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import com.DEVLOP.Application.Mappers.IEquipmentConditionMapper;
import com.DEVLOP.Entities.EquipmentCondition;
import com.DEVLOP.Repositories.EquipmentConditionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class EquipmentConditionCommand {
    private final EquipmentConditionRepo equipmentConditionRepo;

    @Qualifier("IEquipmentConditionMapperImpl")
    private final IEquipmentConditionMapper mapper;

    @Autowired
    public EquipmentConditionCommand(EquipmentConditionRepo equipmentConditionRepo, @Qualifier("IEquipmentConditionMapperImpl") IEquipmentConditionMapper mapper){
        this.equipmentConditionRepo = equipmentConditionRepo;
        this.mapper = mapper;
    }
    @Transactional
    public CompletableFuture<EquipmentConditionDto> CreateNewEquipmentCondition(EquipmentConditionDto equipmentConditionDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentCondition equipmentCondition = mapper.MapToEquipmentCondition(equipmentConditionDto);
            equipmentConditionRepo.PersistEquipmentCondition(equipmentCondition);
            return equipmentConditionDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }

    @Transactional
    public CompletableFuture<EquipmentConditionDto> UpdateEquipmentCondition(EquipmentConditionDto equipmentConditionDto){
        return CompletableFuture.supplyAsync(()->{
            EquipmentCondition equipmentCondition = equipmentConditionRepo.FindEquipmentConditionByID(equipmentConditionDto.getId())
                    .orElseThrow();
            equipmentConditionRepo.PersistEquipmentCondition(mapper.UpdateEquipmentCondition(equipmentConditionDto, equipmentCondition));
            return equipmentConditionDto;
        }).exceptionally(ex ->{
            throw new CompletionException(ex);
        });
    }
}
