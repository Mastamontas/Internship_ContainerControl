package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.EquipmentStatusDto;
import com.DEVLOP.Entities.EquipmentStatus;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IEquipmentStatusMapper {
    @Mapping(source= "id", target="id")
    @Mapping(source= "equipmentStatusCode", target="equipmentStatusCode")
    @Mapping(source= "equipmentStatusName", target="equipmentStatusName")
    @Mapping(source= "equipmentStatusLevel1", target="equipmentStatusLevel1")
    @Mapping(source= "equipmentStatusLevel2", target="equipmentStatusLevel2")
    @Mapping(source= "equipmentStatusComments", target="equipmentStatusComments")
    EquipmentStatus MapToEquipmentStatus(EquipmentStatusDto equipmentStatusDto);

    @Mapping(source= "id", target="id")
    @Mapping(source= "equipmentStatusCode", target="equipmentStatusCode")
    @Mapping(source= "equipmentStatusName", target="equipmentStatusName")
    @Mapping(source= "equipmentStatusLevel1", target="equipmentStatusLevel1")
    @Mapping(source= "equipmentStatusLevel2", target="equipmentStatusLevel2")
    @Mapping(source= "equipmentStatusComments", target="equipmentStatusComments")
    EquipmentStatusDto MapToEquipmentStatusDto(EquipmentStatus equipmentStatus);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source= "id", target="id")
    @Mapping(source= "equipmentStatusCode", target="equipmentStatusCode")
    @Mapping(source= "equipmentStatusName", target="equipmentStatusName")
    @Mapping(source= "equipmentStatusLevel1", target="equipmentStatusLevel1")
    @Mapping(source= "equipmentStatusLevel2", target="equipmentStatusLevel2")
    @Mapping(source= "equipmentStatusComments", target="equipmentStatusComments")
    EquipmentStatus UpdateEquipmentStatus (EquipmentStatusDto equipmentStatusDto, @MappingTarget EquipmentStatus equipmentStatus);
}
