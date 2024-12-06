package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper;

import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS.EquipmentInformationDTO;
import com.devlop.devlop_containercontrol_project.Domain.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Classe mapper
 * DTO->Entity
 * Entity->DTO
 */
@Mapper(componentModel = "spring")
public interface IEquipmentMapper {
/*    //transforma entidade equipamento em DTO
    List<EquipmentInformationDTO> toEquipmentDTOList(List<Equipment> equipmentList);

    //transforma equipamentoDTO em equipamento entity
    Equipment toEquipment (EquipmentInformationDTO eqInfoDTO);

    //transforma equipamento entity em equipamentoDTO
    EquipmentInformationDTO toEquipmentDTO(Equipment equipment);*/
}
