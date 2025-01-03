package com.DEVLOP.Factories;

import com.DEVLOP.DomainEntities.Equipment.Equipment;
import org.junit.Test;


import java.time.Year;
import static org.junit.Assert.*;

public class EquipmentFactoryTest {

    @Test
    public void testCreateEquipment() {
        Equipment equipment = EquipmentFactory.createEquipment(
                1, 2, 3 , "ABC", 12345, 6, 1000.0, 50.0, 2.5, 6.0, 2.4, 200.0, Year.of(2020), 800.0, "Test comment", 3, 4, true);

        assertNotNull(equipment);
        assertEquals(1, equipment.getId());
        assertEquals(2, equipment.getEquipmentTypeID());
        assertEquals("ABC", equipment.getPrefix());
        assertEquals(12345, equipment.getNumber());
        assertEquals(6, equipment.getCheckDigit());
        assertEquals(1000.0, equipment.getGrossWeight(), 0.0);
        assertEquals(50.0, equipment.getInsideCubic(), 0.0);
        assertEquals(2.5, equipment.getInsideHeight(), 0.0);
        assertEquals(6.0, equipment.getInsideLength(), 0.0);
        assertEquals(2.4, equipment.getInsideWidth(), 0.0);
        assertEquals(200.0, equipment.getEquipmentTareWeight(), 0.0);
        assertEquals(Year.of(2020), equipment.getYearOfManufacture());
        assertEquals(800.0, equipment.getPayload(), 0.0);
        assertEquals("Test comment", equipment.getComment());
        assertEquals(3, equipment.getLineID());
        assertEquals(4, equipment.getOwnerID());
        assertTrue(equipment.isSOC());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateEquipmentWithInvalidId() {
        EquipmentFactory.createEquipment(
                -1, 2, 3,"ABC", 12345, 6, 1000.0, 50.0, 2.5, 6.0, 2.4, 200.0, Year.of(2020), 800.0, "Test comment", 3, 4, true);
    }

    // Add more tests for other validation rules as needed
}