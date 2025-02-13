/*
package com.DEVLOP.Repositories;

import com.DEVLOP.PersistenceEntities.EquipmentPersistenceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class IJpaEquipmentRepositoryTest {

    @Autowired
    private IJpaEquipmentRepository iJpaEquipmentRepository;

    @Test
    public void shouldFindAllEquipments() {
        // Given
        EquipmentPersistenceEntity entity1 = new EquipmentPersistenceEntity();
        entity1.setId(1);
        entity1.setPrefix("JBDC");

        EquipmentPersistenceEntity entity2 = new EquipmentPersistenceEntity();
        entity2.setId(2);
        entity2.setPrefix("YMCA");

        iJpaEquipmentRepository.save(entity1);
        iJpaEquipmentRepository.save(entity2);

        // When
        List<EquipmentPersistenceEntity> result = iJpaEquipmentRepository.findAll();

        // Then
        assertEquals(2, result.size());
        assertEquals("JBDC", result.get(0).getPrefix());
        assertEquals("YMCA", result.get(1).getPrefix());
    }
}
*/
