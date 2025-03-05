package com.DEVLOP.Application.Mappers;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Entities.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/*
todo
adicionar lógica de verificação de integridade de dados no mapper também
 */

/**
 * Mapper for conversion of persistence entities into data transfer objects
 */
@Mapper(componentModel = "spring")
public interface IEquipmentMapper {

    @Mapping(source = "id", target ="id")
    @Mapping(source = "prefix", target ="prefix")
    @Mapping(source = "number", target ="number")
    @Mapping(source = "checkDigit", target ="checkDigit")
    @Mapping(source = "grossWeight", target ="grossWeight")
    @Mapping(source = "insideCubic", target ="insideCubic")
    @Mapping(source = "insideHeight", target ="insideHeight")
    @Mapping(source = "insideLength", target ="insideLength")
    @Mapping(source = "insideWidth", target ="insideWidth")
    @Mapping(source = "tareWeight", target ="tareWeight")
    @Mapping(source = "comment", target ="comment")
    @Mapping(source= "equipmentType.equipmentTypeCode", target="equipmentTypeCode")
    @Mapping(source= "equipmentType.equipmentTypeLength", target="equipmentTypeLength")
    @Mapping(source= "equipmentType.equipmentTypeTareWeight", target="equipmentTypeTareWeight")
    @Mapping(source= "equipmentType.equipmentClass.equipmentClassCode", target="equipmentClassCode")
    EquipmentDTO MaptoEquipmentDto(Equipment equipment);



    @Mapping(source = "id", target ="id")
    @Mapping(source = "prefix", target ="prefix")
    @Mapping(source = "number", target ="number")
    @Mapping(source = "checkDigit", target ="checkDigit")
    @Mapping(source = "grossWeight", target ="grossWeight")
    @Mapping(source = "insideCubic", target ="insideCubic")
    @Mapping(source = "insideHeight", target ="insideHeight")
    @Mapping(source = "insideLength", target ="insideLength")
    @Mapping(source = "insideWidth", target ="insideWidth")
    @Mapping(source = "tareWeight", target ="tareWeight")
    @Mapping(source = "comment", target ="comment")
    @Mapping(source = "yearOfManufacture", target ="yearOfManufacture")
    @Mapping(source = "equipmentTypeCode", target = "equipmentType.equipmentTypeCode")
    @Mapping(source = "equipmentTypeLength", target = "equipmentType.equipmentTypeLength")
    @Mapping(source = "equipmentTypeTareWeight", target = "equipmentType.equipmentTypeTareWeight")
    @Mapping(source = "equipmentClassCode", target = "equipmentType.equipmentClass.equipmentClassCode")
    Equipment MapEquipmentDtoToEquipmentEntity(EquipmentDTO eqDTO);


    @Mapping(source = "id", target ="id")
    @Mapping(source = "prefix", target ="prefix")
    @Mapping(source = "number", target ="number")
    @Mapping(source = "checkDigit", target ="checkDigit")
    @Mapping(source = "grossWeight", target ="grossWeight")
    @Mapping(source = "insideCubic", target ="insideCubic")
    @Mapping(source = "insideHeight", target ="insideHeight")
    @Mapping(source = "insideLength", target ="insideLength")
    @Mapping(source = "insideWidth", target ="insideWidth")
    @Mapping(source = "tareWeight", target ="tareWeight")
    @Mapping(source = "comment", target ="comment")
    @Mapping(source = "yearOfManufacture", target ="yearOfManufacture")
    @Mapping(source = "equipmentTypeCode", target = "equipmentType.equipmentTypeCode")
    @Mapping(source = "equipmentTypeLength", target = "equipmentType.equipmentTypeLength")
    @Mapping(source = "equipmentTypeTareWeight", target = "equipmentType.equipmentTypeTareWeight")
    @Mapping(source = "equipmentClassCode", target = "equipmentType.equipmentClass.equipmentClassCode")
    Equipment MapAndUpdateEquipmentFromEquipmentDto(EquipmentDTO eqDTO, @MappingTarget Equipment equipment);
}
