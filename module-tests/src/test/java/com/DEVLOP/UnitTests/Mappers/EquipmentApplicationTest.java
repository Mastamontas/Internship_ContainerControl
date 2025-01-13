package com.DEVLOP.UnitTests.Mappers;

import com.DEVLOP.ContainerMovements.Application.ApplicationMappers.IEquipmentApplicationMapper;
import com.DEVLOP.ContainerMovements.Application.ApplicationMappers.IEquipmentApplicationMapperImpl;
import com.DEVLOP.ContainerMovements.Application.DTOS.EquipmentInformationDTO;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipmentApplicationTest {
    private IEquipmentApplicationMapper mapper;
    private Faker faker;

    @BeforeEach
    void setUp (){
        //porque é que aqui é impl
        mapper = new IEquipmentApplicationMapperImpl();
        faker = new Faker();
    }

    @Test
    void testToDTO(){
        //generate random data with faker
        //random equip data
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


        //mock data
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

        // Perform mapping from Equipment to EquipmentInformationDTO
        EquipmentInformationDTO dto = mapper.toDTO(equipment);
        System.out.println(dto.toString());

        // Perform assertions to ensure mapping is correct
        assertEquals(equipmentTypeCode, dto.getEquipmentTypeCode());
        assertEquals(equipmentTypeLength, dto.getEquipmentTypeLength());
        assertEquals(equipmentTypeTareWeight, dto.getEquipmentTypeTareWeight());
        assertEquals(equipmentClassCode, dto.getEquipmentClassCode());
    }
}
