package com.DEVLOP.UnitTests.Mappers;

import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Application.Mappers.IEquipmentMapperImpl;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationMapperTest {
    private IEquipmentMapper mapper;
    private Faker faker;

    @BeforeEach
    void setUp (){
        //porque é que aqui é impl
        mapper = new IEquipmentMapperImpl();
        faker = new Faker();
    }


    @Test
    void testToDTO(){
        Equipment testEquipment = generateRandomEquipment();
        EquipmentDTO dto = mapper.toDTO(testEquipment);
        assertEquals(testEquipment.getEquipmentTypeID().getEquipmentTypeCode(), dto.getEquipmentTypeCode());
        assertEquals(testEquipment.getEquipmentTypeID().getEquipmentTypeLength(), dto.getEquipmentTypeLength());
        assertEquals(testEquipment.getEquipmentTypeID().getEquipmentTypeTareWeight(), dto.getEquipmentTypeTareWeight());
        assertEquals(testEquipment.getEquipmentTypeID().getEquipmentClassID().getEquipmentClassCode(), dto.getEquipmentClassCode());
    }
    //null test - turn some elements to null and dto generation cannot be complete







    //equipment generator for tests
    private Equipment generateRandomEquipment(){
        int id = faker.number().numberBetween(1,200);
        String prefix = faker.lorem().characters(3).toUpperCase();
        int number = faker.number().numberBetween(1000,9999);
        int checkDigit = faker.number().numberBetween(100,999);
        double grossWeight = faker.number().randomDouble(2,500,2000);
        //random equipment type data
        String equipmentTypeCode =  faker.code().isbn10();
        double equipmentTypeLength = faker.number().randomDouble(2,10,50);
        double equipmentTypeTareWeight  =faker.number().randomDouble(3,10,200);
        //random equipment class data
        String equipmentClassCode= faker.lorem().characters(5).toUpperCase();

        Equipment equipment = new Equipment();
        equipment.setId(id);
        equipment.setPrefix(prefix);
        equipment.setNumber(number);
        equipment.setCheckDigit(checkDigit);
        equipment.setGrossWeight(grossWeight);

        // Set EquipmentTypeID
        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setEquipmentTypeCode(equipmentTypeCode);
        equipmentType.setEquipmentTypeLength(equipmentTypeLength);
        equipmentType.setEquipmentTypeTareWeight(equipmentTypeTareWeight);

        // Set EquipmentClassID
        EquipmentClass equipmentClass = new EquipmentClass();
        equipmentClass.setEquipmentClassCode(equipmentClassCode);

        equipmentType.setEquipmentClassID(equipmentClass); // Linking EquipmentClass to EquipmentTyp
        equipment.setEquipmentTypeID(equipmentType);
        return equipment;
    }
}
