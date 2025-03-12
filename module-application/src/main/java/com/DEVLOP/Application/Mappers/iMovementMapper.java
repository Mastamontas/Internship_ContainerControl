package com.DEVLOP.Application.Mappers;


import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Entities.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface iMovementMapper {
    /*
    movement entity para dto
    movement dto para entity
     */

    //movement
    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="", target="")
    //equipment maps
    @Mapping(source ="equipment.prefix", target="prefix")
    @Mapping(source="equipment.number", target="number")
    @Mapping(source="equipment.checkDigit", target="checkDigit")
    //equipment type maps
    @Mapping(source="equipmentType.equipmentTypeCode", target = "equipmentTypeCode")
    @Mapping(source="equipmentType.equipmentTypeLength", target= "equipmentTypeLength")
    //movement type maps
    //equipment service maps
    //equipment condition maps
    //equipment leasing maps
    //transport means maps
    MovementDto MapToMovementDto(Movement movement);
    Movement MapToMovementEntity(MovementDto movementDto);

}
