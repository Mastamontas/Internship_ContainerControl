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

import java.time.Year;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/*
todo - arrange act assert
tests of validation for mappings done in application layer
have into consideration that when converting the dto to an equipment, the relationships within the equipment
    class have to be maintained. You have to find the entities associated with the eqType and eqClass in the equipment and
    map them correctly

    dto needs to have an ID so it can map to an existing entity
    does it make sense to hard code ID's in the generate equipment/generate equipmentDTO in order for them to access it
    easily? - yes, as you are not testing persistence


Change naming conventions to Pascal Case


 */
/*
todo - URGENTE
mockar para unit tests
 */
public class ApplicationMapperTest {
    private IEquipmentMapper mapper;
    private Faker faker;

    @BeforeEach
    void setUp (){
        mapper = new IEquipmentMapperImpl();
        faker = new Faker();
    }


    @Test
    void testToDTO_ReturnValidDTO(){
        Equipment testEquipment = generateRandomEquipment();
        EquipmentDTO dto = mapper.MaptoEquipmentDto(testEquipment);
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

    /*
    todo
     */
    @Test
    void testToEquipment_ReturnValidEquipment(){
        EquipmentDTO eqDTO = generateRandomEquipmentDTO();
        Equipment eq = mapper.MapEquipmentDtoToEquipmentEntity(eqDTO);
    }


    @Test
    void testMapAndUpdateEquipment_MapsEquipmentCorrectly(){
        /*
        todo
        rever nomenclatura: consistencia entre atributos entidade e DTO's
        equipmentDTO Tare prone to errors with Equipment EquipmentTareWeight. Name them the same
         */

        Equipment eq = generateRandomEquipment();
        double originalTare = eq.getTareWeight();
        int originalID = eq.getId();
        String originalComment = eq.getComment();
        System.out.println(originalComment);
        String originalPrefix = eq.getPrefix();
        EquipmentDTO eqDTOFromDB= mapper.MaptoEquipmentDto(eq);
        double newTareValue = 21.00;
        String newComment = "New Comment";
        eqDTOFromDB.setTareWeight(newTareValue);
        System.out.println(eqDTOFromDB.getTareWeight());
        eqDTOFromDB.setComment(newComment);
        System.out.println(eqDTOFromDB.getComment());
        Equipment updatedEquipment = mapper.MapAndUpdateEquipmentFromEquipmentDto(eqDTOFromDB,eq);
        System.out.println(updatedEquipment.getComment());

        assertEquals(newTareValue, updatedEquipment.getTareWeight(), "Updated equipment should have the new tare value");
        assertNotEquals(originalTare, updatedEquipment.getTareWeight(), "Original equipment and updated equipment should have different tare values");
        assertEquals(originalID, updatedEquipment.getId(), "Original equipment and updated equipment should have same ID values");
        assertEquals(originalPrefix, updatedEquipment.getPrefix(), "Original equipment and updated equipment should have same prefix values");
        assertNull(originalComment, "Equipment comment from db should be null");
        assertNotNull(updatedEquipment.getComment(), "Comment from update equipment should not be null");
        assertEquals(eqDTOFromDB.getComment(), updatedEquipment.getComment(), "Comment from dto and updated equipment should match");
        assertNotEquals(originalComment, updatedEquipment.getComment(), "updated equipment comment should not match original eq comment");



    };

    //equipment generator for tests
    private Equipment generateRandomEquipment(){
        int id = 1;
        String prefix = faker.regexify("[A-Z]{4}");
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

        equipmentType.setEquipmentClass(equipmentClass); // Linking EquipmentClass to EquipmentTyp
        equipment.setEquipmentType(equipmentType);
        return equipment;
    }
    /*
    not generate random, generate a dto from an existing entity
     */
    private EquipmentDTO generateRandomEquipmentDTO(){
        EquipmentDTO dto = new EquipmentDTO();
        /*
        hard coded, we dont fetch from the persistence because this tests only the mapper
         */
        dto.setId(1);
        dto.setPrefix(faker.regexify("[A-Z]{4}")); // Generates a valid 4-letter uppercase prefix
        dto.setNumber(faker.number().numberBetween(1000, 9999));
        dto.setCheckDigit(faker.number().numberBetween(0, 9));
        dto.setInsideHeight(faker.number().randomDouble(2, 2, 10));
        dto.setGrossWeight(faker.number().randomDouble(2, 1000, 50000));
        dto.setPayload(faker.number().randomDouble(2, 1000, 50000));
        dto.setTareWeight(0.00);
        dto.setInsideLength(faker.number().randomDouble(2, 2, 20));
        dto.setInsideWidth(faker.number().randomDouble(2, 2, 10));
        dto.setInsideCubic(faker.number().randomDouble(2, 10, 100));
        dto.setYearOfManufacture(Year.of(faker.number().numberBetween(1980, 2024)));
        dto.setComment(faker.lorem().sentence());

        // Equipment type fields
        dto.setEquipmentTypeCode(faker.bothify("EQ-##??"));
        dto.setEquipmentTypeLength(faker.number().randomDouble(2, 10, 50));
        dto.setEquipmentTypeTareWeight(faker.number().randomDouble(2, 500, 5000));

        // Equipment class field
        dto.setEquipmentClassCode(faker.bothify("EC-##??"));

        return dto;
    }
}
