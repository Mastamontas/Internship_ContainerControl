package com.DEVLOP.Application.Mappers;


import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Entities.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMovementMapper {
    /*
    movement entity para dto
    movement dto para entity
     */

    //movement
    /*
    movement key?
    movement status
    equipment condition/condition code- equipment condition entity
     */
    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementType.movementTypeCode", target="movementCode")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="movementComment", target="comments")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    //equipment maps
    @Mapping(source ="equipment.prefix", target="prefix")
    @Mapping(source="equipment.number", target="number")
    @Mapping(source="equipment.checkDigit", target="checkDigit")
    //equipment service maps
    @Mapping(source="equipmentService.equipmentServiceCode", target="equipmentServiceCode")
    //equipment type maps
    @Mapping(source="equipmentType.equipmentTypeCode", target = "equipmentTypeCode")
    @Mapping(source="equipmentType.equipmentTypeLength", target= "equipmentTypeLength")
    @Mapping(source = "equipmentCondition.physicalConditionCode", target="conditionCode")
    MovementDto MapToMovementDto(Movement movement);

    //Movement MapToMovementEntity(MovementDto movementDto);

}
