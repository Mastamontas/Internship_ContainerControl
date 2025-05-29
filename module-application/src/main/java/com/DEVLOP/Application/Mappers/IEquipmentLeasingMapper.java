package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
import com.DEVLOP.Entities.EquipmentLeasing;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IEquipmentLeasingMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "leasingContractCode", source = "leasingContractCode")
    @Mapping(target = "leasingContractName", source = "leasingContractName")
    EquipmentLeasingDto MapToEquipmentLeasingDto(EquipmentLeasing equipmentLeasing);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "leasingContractCode", source = "leasingContractCode")
    @Mapping(target = "leasingContractName", source = "leasingContractName")
    EquipmentLeasing MapToEquipmentLeasing(EquipmentLeasingDto equipmentLeasingDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "leasingContractCode", source = "leasingContractCode")
    @Mapping(target = "leasingContractName", source = "leasingContractName")
    EquipmentLeasing UpdateEquipmentLeasing (EquipmentLeasingDto equipmentLeasingDto,@MappingTarget EquipmentLeasing equipmentLeasing);


}
