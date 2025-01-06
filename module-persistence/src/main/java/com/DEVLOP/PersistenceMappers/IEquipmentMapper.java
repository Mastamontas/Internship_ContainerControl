package com.DEVLOP.PersistenceMappers;

import com.DEVLOP.DomainEntities.Equipment.Equipment;
import com.DEVLOP.PersistenceEntities.EquipmentPersistenceEntity;
import com.DEVLOP.PersistenceEntities.EquipmentTypePersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IEquipmentMapper {
    @Mapping(source="equipmentTypeID.equipmentTypeCode", target = "equipmentTypeCode")
    @Mapping(source ="equipmentTypeID.equipmentTypeLength",target="equipmentTypeLength")
    @Mapping(source="equipmentTypeID.equipmentTypeTareWeight", target ="equipmentTypeTareWeight")
    @Mapping(source="equipmentTypeID.equipmentClassID.equipmentClassCode", target="equipmentClassCode")
    Equipment toDomainEntity(EquipmentPersistenceEntity equipmentPersistenceEntity);
}
