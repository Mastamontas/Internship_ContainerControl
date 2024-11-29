package com.devlop.devlop_containercontrol_project.ContainerMovements.Application.Mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IEquipmentMapper {
    /**
     * responsavel pela conversao de equipmentList entity para equipmentList dto
     * MapStruct will automatically create a method implementation to iterate over the equipmentList and
     * apply the mapping logic from Equipment to EquipmentInformationDTO and from
     * equipmentDTO to equipment entity.
     */
    //List<EquipmentInformationDTO> toEquipmentDTOList(List<Equipment> equipmentList);


    //update ao equipamento sem alterar campos que venham a null
    //void updateEquipmentFromDTO(EquipmentInformationDTO eqDTO, @MappingTarget Equipment eqEntity);






}
