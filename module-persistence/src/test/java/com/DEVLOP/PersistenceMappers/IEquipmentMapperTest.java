package com.DEVLOP.PersistenceMappers;

import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IEquipmentMapperTest {

    private final IEquipmentPersistenceMapper mapper = Mappers.getMapper(IEquipmentPersistenceMapper.class);

/*    @Test
    public void shouldMapPersistenceEntityToDomainEntity() {
        // Arrange: Set up the input persistence entity
        com.DEVLOP.Entities.Equipment persistenceEntity = new com.DEVLOP.Entities.Equipment();
        persistenceEntity.setId(1);
        EquipmentType typeEntity = new EquipmentType();
        typeEntity.setId(42);
        persistenceEntity.setEquipmentTypeID(typeEntity);

        // Act: Perform the mapping
        Equipment result = mapper.toDomainEntity(persistenceEntity);

        // Assert: Verify the result
        assertEquals(1, result.getId());
        *//*assertEquals(42, result.getEquipmentTypeID());*//*
    }*/
}
