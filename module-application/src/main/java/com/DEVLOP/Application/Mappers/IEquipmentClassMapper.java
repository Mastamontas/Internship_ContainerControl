package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IEquipmentClassMapper {

    @Mapping(source ="id", target ="id")
    @Mapping(source ="equipmentClassCode", target ="equipmentClassCode")
    @Mapping(source ="equipmentClassName", target ="equipmentClassName")
    @Mapping(source ="equipmentClassType", target ="equipmentClassType")
    EquipmentClass MapToEquipmentClass(EquipmentClassDto equipmentClassDto);

    @Mapping(source ="id", target ="id")
    @Mapping(source ="equipmentClassCode", target ="equipmentClassCode")
    @Mapping(source ="equipmentClassName", target ="equipmentClassName")
    @Mapping(source ="equipmentClassType", target ="equipmentClassType")
    EquipmentClassDto MapToEquipmentClassDto(EquipmentClass equipmentClass);

    @Mapping(source ="id", target ="id")
    @Mapping(source ="equipmentClassCode", target ="equipmentClassCode")
    @Mapping(source ="equipmentClassName", target ="equipmentClassName")
    @Mapping(source ="equipmentClassType", target ="equipmentClassType")
    EquipmentClass UpdateEquipmentClass(EquipmentClassDto equipmentClassDto, @MappingTarget EquipmentClass equipmentClass);



}
