package com.DEVLOP.UnitTests.Mappers;

import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.Mappers.IEquipmentMapperImpl;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Factories.EquipmentFactory;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/*
mockar para unit tests
refactor nomes testes

 */
public class EquipmentMapperTest {
    private IEquipmentMapper mapper;
    private Faker faker;

    @BeforeEach
    void setUp (){
        mapper = new IEquipmentMapperImpl();
        faker = new Faker();
    }


    @Test
    void testToDTO_ReturnValidDTO(){
        Equipment testEquipment = EquipmentFactory.CreateEquipment();
        EquipmentDto dto = mapper.MaptoEquipmentDto(testEquipment);
        /*
        todo
        add more verifications to this test as it only reveals the explicit mapped entities
         */
        assertEquals(testEquipment.getId(), dto.getId());
        assertEquals(testEquipment.getPrefix(), dto.getPrefix());
        assertEquals(testEquipment.getNumber(), dto.getNumber());

        assertEquals(testEquipment.getEquipmentType().getEquipmentTypeCode(), dto.getEquipmentTypeCode());
        assertEquals(testEquipment.getEquipmentType().getEquipmentTypeLength(), dto.getEquipmentTypeLength());
        assertEquals(testEquipment.getEquipmentType().getEquipmentTypeTareWeight(), dto.getEquipmentTypeTareWeight());
        assertEquals(testEquipment.getEquipmentType().getEquipmentClass().getEquipmentClassCode(), dto.getEquipmentClassCode());
    }


    @Test
    void testMapAndUpdateEquipment_MapsEquipmentCorrectly(){
        /*
        todo
        rever nomenclatura: consistencia entre atributos entidade e DTO's
        equipmentDTO Tare prone to errors with Equipment EquipmentTareWeight. Name them the same
         */

        Equipment eq = EquipmentFactory.CreateEquipment();
        double originalTare = eq.getTareWeight();
        int originalID = eq.getId();
        String originalComment = eq.getComment();

        String originalPrefix = eq.getPrefix();
        EquipmentDto eqDTOFromDB= mapper.MaptoEquipmentDto(eq);
        double newTareValue = 21.00;
        String newComment = "New Comment";
        eqDTOFromDB.setTareWeight(newTareValue);

        eqDTOFromDB.setComment(newComment);

        Equipment updatedEquipment = mapper.MapAndUpdateEquipmentFromEquipmentDto(eqDTOFromDB,eq);


        assertEquals(newTareValue, updatedEquipment.getTareWeight(), "Updated equipment should have the new tare value");
        assertNotEquals(originalTare, updatedEquipment.getTareWeight(), "Original equipment and updated equipment should have different tare values");
        assertEquals(originalID, updatedEquipment.getId(), "Original equipment and updated equipment should have same ID values");
        assertEquals(originalPrefix, updatedEquipment.getPrefix(), "Original equipment and updated equipment should have same prefix values");
        assertNull(originalComment, "Equipment comment from db should be null");
        assertNotNull(updatedEquipment.getComment(), "Comment from update equipment should not be null");
        assertEquals(eqDTOFromDB.getComment(), updatedEquipment.getComment(), "Comment from dto and updated equipment should match");
        assertNotEquals(originalComment, updatedEquipment.getComment(), "updated equipment comment should not match original eq comment");
    };
}
