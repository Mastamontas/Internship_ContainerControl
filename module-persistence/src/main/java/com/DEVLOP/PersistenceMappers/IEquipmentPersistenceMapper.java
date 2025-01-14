package com.DEVLOP.PersistenceMappers;

import com.DEVLOP.Entities.Equipment;
import org.mapstruct.Mapper;

/**
 * Mapper for conversion of DTO equipment entities in equipment persistence entities
 */
@Mapper(componentModel = "spring")
public interface IEquipmentPersistenceMapper {
    //Equipment toPersistenceEntity(com.DEVLOP.Entities.Equipment equipmentEntity);
}
