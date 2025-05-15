package com.DEVLOP.Application.Mappers;


import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentCondition;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IEquipmentConditionMapper {

    @Mapping(source ="id", target ="id")
    @Mapping(source ="physicalConditionCode", target ="physicalConditionCode")
    @Mapping(source ="physicalConditionName", target ="physicalConditionName")
    @Mapping(source ="physicalConditionType", target ="physicalConditionType")
    EquipmentCondition MapToEquipmentCondition(EquipmentConditionDto equipmentConditionDto);
    @Mapping(source ="id", target ="id")
    @Mapping(source ="physicalConditionCode", target ="physicalConditionCode")
    @Mapping(source ="physicalConditionName", target ="physicalConditionName")
    @Mapping(source ="physicalConditionType", target ="physicalConditionType")
    EquipmentConditionDto MapToEquipmentConditionDto(EquipmentCondition equipmentCondition);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source ="id", target ="id")
    @Mapping(source ="physicalConditionCode", target ="physicalConditionCode")
    @Mapping(source ="physicalConditionName", target ="physicalConditionName")
    @Mapping(source ="physicalConditionType", target ="physicalConditionType")
    EquipmentCondition UpdateEquipmentCondition(EquipmentConditionDto equipmentConditionDto, @MappingTarget EquipmentCondition equipmentCondition);
}
