package com.DEVLOP.ContainerMovements.Application.Mappers;
import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Entities.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


/**
 * Mapper for conversion of persistence entities into data transfer objects
 */
@Mapper(componentModel = "spring")
public interface IEquipmentMapper {
    @Mapping(source="equipmentTypeID.equipmentTypeCode", target="equipmentTypeCode")
    @Mapping(source="equipmentTypeID.equipmentTypeLength", target="equipmentTypeLength")
    @Mapping(source="equipmentTypeID.equipmentTypeTareWeight", target="equipmentTypeTareWeight")
    @Mapping(source="equipmentTypeID.equipmentClassID.equipmentClassCode", target="equipmentClassCode")
    EquipmentDTO toDTO(Equipment equipment);


    //definir mapeamentos
    @Mapping(source="equipmentTypeCode", target="equipmentTypeID.equipmentTypeCode")
    @Mapping(source="equipmentTypeLength", target="equipmentTypeID.equipmentTypeLength")
    @Mapping(source="equipmentTypeTareWeight", target="equipmentTypeID.equipmentTypeTareWeight")
    @Mapping(source="equipmentClassCode", target="equipmentTypeID.equipmentClassID.equipmentClassCode")
    Equipment toEquipment(EquipmentDTO eqDTO);

    //mapping to update equipment information
    //permite mapeamento automatico de atributos pela anotacao mapping target
    @Mapping(source = "equipmentTypeCode", target = "equipmentTypeID.equipmentTypeCode")
    @Mapping(source = "equipmentTypeLength", target = "equipmentTypeID.equipmentTypeLength")
    @Mapping(source = "equipmentTypeTareWeight", target = "equipmentTypeID.equipmentTypeTareWeight")
    @Mapping(source = "equipmentClassCode", target = "equipmentTypeID.equipmentClassID.equipmentClassCode")
    Equipment updateEquipmentFromDTO(EquipmentDTO eqDTO, @MappingTarget Equipment equipment);
}
