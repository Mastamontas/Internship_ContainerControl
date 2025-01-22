package com.DEVLOP.ContainerMovements.Application.Commands;

import com.DEVLOP.ContainerMovements.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentDTO;
import com.DEVLOP.ContainerMovements.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Repositories.EquipmentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class EquipmentCommand {

    private final IEquipmentMapper iEquipmentMapper;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentCommand(@Qualifier("IEquipmentMapperImpl") IEquipmentMapper iEquipmentMapper, EquipmentRepository equipmentRepository) {
        this.iEquipmentMapper = iEquipmentMapper;
        this.equipmentRepository = equipmentRepository;
    }

    /*persisting the editted equipment*/
    //isto é equipment command
    //recebe o DTO e transforma em equipment para ser persistido na DB
    private Equipment mapUpdatedDTOToEq(@Valid EquipmentDTO eqDTO){
        Equipment eq = fetchEquipmentByPrefix(eqDTO.getPrefix());
        return iEquipmentMapper.updateEquipmentFromDTO(eqDTO, eq);
    }

    /*
    TODO
    unique prefix- method can call prefix
    non-unique prefixes - method has to select equipment according to prefix, number and check digit
     */
    private Equipment fetchEquipmentByPrefix (String prefix){
        try{
            return equipmentRepository.findByPrefix(prefix);
        } catch (EquipmentNotFoundException e){
            throw new EquipmentNotFoundException("No equipment has that prefix");
        }
    }

    //get equipment by prefix
    //we assume the prefix is unique
    //prefix tem de ter regras de validacao
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
