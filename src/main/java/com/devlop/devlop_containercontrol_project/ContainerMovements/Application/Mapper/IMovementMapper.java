package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper;

import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS.AggregatedInformationDTO;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS.EquipmentMovementDTO;
import com.devlop.devlop_containercontrol_project.ContainerMovements.Application.DTOS.MovementInformationDTO;
import com.devlop.devlop_containercontrol_project.Domain.Equipment;
import com.devlop.devlop_containercontrol_project.Domain.Movement;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
//objetivo deste mapper é a conversão de movimentos de equipamentos para movimentos de entidades e vice versa
public interface IMovementMapper{
/*    //retorna os movimentos de Entidade para DTO
    List<EquipmentMovementDTO> toEquipmentMovementsDTOList(List<Movement> equipmentMovementsList);
    //converter de aggregated DTO para movement entity
    Movement toMovementEntity(EquipmentMovementDTO equipMoveDTO);
    //também dá para passar uma lista de movimentos DTO para uma lista de entidades
    List<Movement> toMovementEntityList(List<MovementInformationDTO> moveInfoDTO);
    //converter de aggregatedDTO para movement DTO
    EquipmentMovementDTO fromAggregatedToMoveDTO(AggregatedInformationDTO agDTO);*/
}
