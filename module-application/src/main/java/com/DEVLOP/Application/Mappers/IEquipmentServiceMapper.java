package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.EquipmentServiceDto;
import com.DEVLOP.Entities.EquipmentService;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IEquipmentServiceMapper {
    @Mapping(source = "id" , target = "id")
    @Mapping(source = "equipmentServiceCode" , target = "equipmentServiceCode")
    @Mapping(source = "equipmentServiceName" , target = "equipmentServiceName")
    @Mapping(source = "equipmentServiceComments" , target = "equipmentServiceComments")
    EquipmentService MapToEquipmentService(EquipmentServiceDto equipmentServiceDto);

    @Mapping(source = "id" , target = "id")
    @Mapping(source = "equipmentServiceCode" , target = "equipmentServiceCode")
    @Mapping(source = "equipmentServiceName" , target = "equipmentServiceName")
    @Mapping(source = "equipmentServiceComments" , target = "equipmentServiceComments")
    EquipmentServiceDto MapToEquipmentServiceDto(EquipmentService equipmentService);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "id" , target = "id")
    @Mapping(source = "equipmentServiceCode" , target = "equipmentServiceCode")
    @Mapping(source = "equipmentServiceName" , target = "equipmentServiceName")
    @Mapping(source = "equipmentServiceComments" , target = "equipmentServiceComments")
    EquipmentService UpdateEquipmentService(EquipmentServiceDto equipmentServiceDto, @MappingTarget EquipmentService equipmentService);
}
