package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Interfaces.Commands.IEquipmentCommands;
import com.DEVLOP.Repositories.EquipmentRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/*
todo: create equipment has to be possible, needs equipment type
 */
@Service
public class EquipmentCommand implements IEquipmentCommands {

    private final IEquipmentMapper iEquipmentMapper;
    private final EquipmentRepo equipmentRepo;

    @Autowired
    public EquipmentCommand(@Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper, EquipmentRepo equipmentRepo) {
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentRepo = equipmentRepo;
    }

    @Override
    @Transactional
    public CompletableFuture<com.DEVLOP.Entities.Equipment> UpdateEquipment(int id, @Valid EquipmentDto equipmentDTO){
        return CompletableFuture.supplyAsync(()-> {
            com.DEVLOP.Entities.Equipment eq = equipmentRepo.FindByID(id).orElseThrow(()->
                    new EquipmentNotFoundException("Equipment with that ID is not found"));
            return iEquipmentMapper.MapAndUpdateEquipmentFromEquipmentDto(equipmentDTO, eq);
        }).thenApplyAsync(updatedEq ->{
            equipmentRepo.PersistEquipment(updatedEq);
            return updatedEq;
        });
    }
}
