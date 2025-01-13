package com.DEVLOP.ContainerMovements.Application.ApplicationMappers;
import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentInformationDTO;
import com.DEVLOP.Entities.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper for conversion of persistence entities into data transfer objects
 */
@Mapper(componentModel = "spring")
public interface IEquipmentApplicationMapper {
    @Mapping(source="equipmentTypeID.equipmentTypeCode", target="equipmentTypeCode")
    @Mapping(source="equipmentTypeID.equipmentTypeLength", target="equipmentTypeLength")
    @Mapping(source="equipmentTypeID.equipmentTypeTareWeight", target="equipmentTypeTareWeight")
    @Mapping(source="equipmentTypeID.equipmentClassID.equipmentClassCode", target="equipmentClassCode")
    EquipmentInformationDTO toDTO(Equipment equipment);

}
