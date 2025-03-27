package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.Commands.IEquipmentCommands;
import com.DEVLOP.Repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EquipmentCommand implements IEquipmentCommands {

    private final IEquipmentMapper iEquipmentMapper;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentCommand(@Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper, EquipmentRepository equipmentRepository) {
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    @Transactional
    public CompletableFuture<Equipment> UpdateEquipment(int id, @Valid EquipmentDto equipmentDTO){
        return CompletableFuture.supplyAsync(()-> {
            Equipment eq = equipmentRepository.FindByID(id).orElseThrow(()->
                    new EquipmentNotFoundException("Equipment with that ID is not found"));
            return iEquipmentMapper.MapAndUpdateEquipmentFromEquipmentDto(equipmentDTO, eq);
        }).thenApplyAsync(updatedEq ->{
            equipmentRepository.PersistEquipment(updatedEq);
            return updatedEq;
        });
    }
}
