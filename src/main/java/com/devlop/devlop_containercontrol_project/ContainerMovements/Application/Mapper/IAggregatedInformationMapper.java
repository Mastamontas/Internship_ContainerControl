package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses ={IMovementMapper.class, IEquipmentMapper.class})
public interface IAggregatedInformationMapper {
    //mapper para aggregatedInformationDTO
    /**
     * AggregatedInformationDTO toAggregatedInformationDTO(EquipmentInformationDTO eInfoDTO, EquipmentMovementDTO eMoveDTO);
     */
}
