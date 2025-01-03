package com.DEVLOP.PersistenceMappers;

import com.DEVLOP.DomainEntities.Equipment.Equipment;
import com.DEVLOP.PersistenceEntities.EquipmentPersistenceEntity;
import com.DEVLOP.PersistenceEntities.EquipmentTypePersistenceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IEquipmentMapper {
    //faltam aqui os mappings para os IDs dos owners
    //faltam retornar o codigo do equipment type, nao o seu id
    //desenhar a query primeiro, depois o mapper
    @Mapping(source="equipmentTypeID.id", target = "equipmentTypeID")
    Equipment toDomainEntity(EquipmentPersistenceEntity equipmentPersistenceEntity);

    //EquipmentPersistenceEntity toPersistenceEntity(Equipment equipment);

    /*@Named("mapEquipmentType")
    default EquipmentTypePersistenceEntity mapEquipmentType(int value){
        EquipmentTypePersistenceEntity equipmentType = new EquipmentTypePersistenceEntity();
        equipmentType.setId(value);
        return equipmentType;
    }*/
}
