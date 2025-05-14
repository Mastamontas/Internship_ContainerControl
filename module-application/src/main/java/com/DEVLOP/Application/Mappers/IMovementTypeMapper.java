package com.DEVLOP.Application.Mappers;

import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Entities.MovementType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IMovementTypeMapper {

    @Mapping(source="id", target="id")
    @Mapping(source="movementTypeCode", target="movementTypeCode")
    @Mapping(source="movementTypeName", target="movementTypeName")
    @Mapping(source="movementTypeComments", target="movementTypeComments")
    @Mapping(source="empty", target="empty")
    MovementTypeDto MapToMovementTypeDto(MovementType movementType);


    @Mapping(source="id", target="id")
    @Mapping(source="movementTypeCode", target="movementTypeCode")
    @Mapping(source="movementTypeName", target="movementTypeName")
    @Mapping(source="movementTypeComments", target="movementTypeComments")
    @Mapping(source="empty", target="empty")
    MovementType MapToMovementType(MovementTypeDto movementTypeDto);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source="id", target="id")
    @Mapping(source="movementTypeCode", target="movementTypeCode")
    @Mapping(source="movementTypeName", target="movementTypeName")
    @Mapping(source="movementTypeComments", target="movementTypeComments")
    @Mapping(source="empty", target="empty")
    MovementType UpdateMovementType(MovementTypeDto movementTypeDto, @MappingTarget MovementType movementType);
}
