package com.DEVLOP.PersistenceMappers;

import com.DEVLOP.DomainEntities.Equipment.Equipment;
import com.DEVLOP.PersistenceEntities.EquipmentPersistenceEntity;
import com.DEVLOP.PersistenceEntities.EquipmentTypePersistenceEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IEquipmentMapperTest {

    private final IEquipmentMapper mapper = Mappers.getMapper(IEquipmentMapper.class);

    @Test
    public void shouldMapPersistenceEntityToDomainEntity() {
        // Arrange: Set up the input persistence entity
        EquipmentPersistenceEntity persistenceEntity = new EquipmentPersistenceEntity();
        persistenceEntity.setId(1);
        EquipmentTypePersistenceEntity typeEntity = new EquipmentTypePersistenceEntity();
        typeEntity.setId(42);
        persistenceEntity.setEquipmentTypeID(typeEntity);

        // Act: Perform the mapping
        Equipment result = mapper.toDomainEntity(persistenceEntity);

        // Assert: Verify the result
        assertEquals(1, result.getId());
        assertEquals(42, result.getEquipmentTypeID());
    }
}
