package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Application.DTOS.EquipmentTypeDto;
import com.DEVLOP.Entities.EquipmentType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IEquipmentTypeMapper {
    @Mapping(source = "id", target ="id")
    @Mapping(source = "equipmentTypeCode", target ="equipmentTypeCode")
    @Mapping(source = "equipmentTypeName", target ="equipmentTypeName")
    @Mapping(source = "equipmentTypeLength", target ="equipmentTypeLength")
    @Mapping(source = "equipmentTypeHeight", target ="equipmentTypeHeight")
    @Mapping(source = "equipmentTypeComments", target ="equipmentTypeComments")
    EquipmentTypeDto MapToEquipmentTypeDto(EquipmentType equipmentType);

    @Mapping(source = "id", target ="id")
    @Mapping(source = "equipmentTypeCode", target ="equipmentTypeCode")
    @Mapping(source = "equipmentTypeName", target ="equipmentTypeName")
    @Mapping(source = "equipmentTypeLength", target ="equipmentTypeLength")
    @Mapping(source = "equipmentTypeHeight", target ="equipmentTypeHeight")
    @Mapping(source = "equipmentTypeComments", target ="equipmentTypeComments")
    EquipmentType MapToEquipmentType (EquipmentTypeDto equipmentTypeDto);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id", target ="id")
    @Mapping(source = "equipmentTypeCode", target ="equipmentTypeCode")
    @Mapping(source = "equipmentTypeName", target ="equipmentTypeName")
    @Mapping(source = "equipmentTypeLength", target ="equipmentTypeLength")
    @Mapping(source = "equipmentTypeHeight", target ="equipmentTypeHeight")
    @Mapping(source = "equipmentTypeComments", target ="equipmentTypeComments")
    EquipmentType UpdateEquipmentType(EquipmentTypeDto equipmentTypeDto, @MappingTarget EquipmentType equipmentType);


}
