package com.DEVLOP.Application.Commands;

import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Interfaces.ICommands;
import com.DEVLOP.Repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EquipmentCommand {

    private final IEquipmentMapper iEquipmentMapper;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentCommand(@Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper, EquipmentRepository equipmentRepository) {
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentRepository = equipmentRepository;
    }

    private Equipment mapUpdatedDTOToEq(@Valid EquipmentDTO eqDTO){
        Equipment eq = fetchEquipmentByPrefix(eqDTO.getPrefix());
        return iEquipmentMapper.updateEquipmentFromDTO(eqDTO, eq);
    }

    private Equipment fetchEquipmentByPrefix (String prefix){
        try{
            return equipmentRepository.findByPrefix(prefix);
        } catch (EquipmentNotFoundException e){
            throw new EquipmentNotFoundException("No equipment has that prefix");
        }
    }

    @Transactional
    public void updateEquipment(EquipmentDTO eqDTO){
        try{
            Equipment eq = mapUpdatedDTOToEq(eqDTO);
            equipmentRepository.updateEquipment(eq);
        } catch (EquipmentNotFoundException e){
            throw new EquipmentNotFoundException("Unsucessfull equipment update" + e.getMessage());
        }
    }
}
