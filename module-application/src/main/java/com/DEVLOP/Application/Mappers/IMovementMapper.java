package com.DEVLOP.Application.Mappers;


import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Entities.Movement;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IMovementMapper {
    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementType.movementTypeCode", target="movementCode")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="movementComment", target="comments")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    //equipment maps
    @Mapping(source="equipment.id", target="equipmentId")
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


    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementCode", target="movementType.movementTypeCode")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="comments", target="movementComment")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    //equipment maps
    @Mapping(source="equipmentId", target ="equipment.id")
    @Mapping(source ="prefix", target="equipment.prefix")
    @Mapping(source="number", target="equipment.number")
    @Mapping(source="checkDigit", target="equipment.checkDigit")
    //equipment service maps
    @Mapping(source="equipmentServiceCode", target="equipmentService.equipmentServiceCode")
    //equipment type maps
    @Mapping(source="equipmentTypeCode", target = "equipmentType.equipmentTypeCode")
    @Mapping(source="equipmentTypeLength", target= "equipmentType.equipmentTypeLength")
    @Mapping(source = "conditionCode", target="equipmentCondition.physicalConditionCode")
    Movement MapToMovementEntity(MovementDto movementDto);//create new movements for specific equipments

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="id", target = "id")
    @Mapping(source="date", target="date")
    @Mapping(source="movementCode", target="movementType.movementTypeCode")
    //@Mapping(source="movementType.isEmpty", target="isEmpty")
    @Mapping(source="comments", target="movementComment")
    @Mapping(source="transportResponsibility", target="transportResponsibility")
    //equipment maps
    @Mapping(source="equipmentId", target ="equipment.id")
    @Mapping(source ="prefix", target="equipment.prefix")
    @Mapping(source="number", target="equipment.number")
    @Mapping(source="checkDigit", target="equipment.checkDigit")
    //equipment service maps
    @Mapping(source="equipmentServiceCode", target="equipmentService.equipmentServiceCode")
    //equipment type maps
    @Mapping(source="equipmentTypeCode", target = "equipmentType.equipmentTypeCode")
    @Mapping(source="equipmentTypeLength", target= "equipmentType.equipmentTypeLength")
    @Mapping(source = "conditionCode", target="equipmentCondition.physicalConditionCode")
    Movement UpdateMovementEntity(MovementDto moveDto, @MappingTarget Movement move);

}
