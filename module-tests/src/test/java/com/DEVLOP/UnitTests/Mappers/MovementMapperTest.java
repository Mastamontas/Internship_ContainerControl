package com.DEVLOP.UnitTests.Mappers;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MovementMapperTest {
    private IMovementMapper mapper;

    @BeforeEach
    void SetUp(){
        mapper = new IMovementMapperImpl();
    }
    @Test
    public void MapsMovementCorrectly(){
        //arrange
        Equipment eq = EquipmentFactory.CreateEquipment();
        eq.setId(1);
        Movement mov = MovementFactory.CreateMovementEntity(eq);
        mov.setId(1);
        //act
        MovementDto movementDto = mapper.MapToMovementDto(mov);
        assertNotNull(movementDto, "Mapped MovementDto should not be null");
        assertEquals(mov.getId(), movementDto.getId(), "ID should be correctly mapped");
        assertEquals(mov.getDate(), movementDto.getDate(), "Date should be correctly mapped");
        assertEquals(mov.getMovementType().getMovementTypeCode(), movementDto.getMovementCode(), "MovementTypeCode should be mapped correctly");
        assertEquals(mov.getMovementType().isEmpty(), movementDto.isEmpty(), "isEmpty should be mapped correctly");
        assertEquals(mov.getMovementComment(), movementDto.getComments(), "Comments should be mapped correctly");
        assertEquals(mov.getTransportResponsibility(), movementDto.getTransportResponsibility(), "TransportResponsibility should be mapped correctly");

        // Equipment mappings
        assertEquals(eq.getPrefix(), movementDto.getPrefix(), "Equipment prefix should be mapped correctly");
        assertEquals(eq.getNumber(), movementDto.getNumber(), "Equipment number should be mapped correctly");
        assertEquals(eq.getCheckDigit(), movementDto.getCheckDigit(), "Check digit should be mapped correctly");

        // Equipment Service mappings
        assertEquals(mov.getEquipmentService().getEquipmentServiceCode(), movementDto.getEquipmentServiceCode(), "Equipment service code should be mapped correctly");

        // Equipment Type mappings
        assertEquals(mov.getEquipmentType().getEquipmentTypeCode(), movementDto.getEquipmentTypeCode(), "EquipmentTypeCode should be mapped correctly");
        assertEquals(mov.getEquipmentType().getEquipmentTypeLength(), movementDto.getEquipmentTypeLength(), "EquipmentTypeLength should be mapped correctly");

        // Condition mappings
        assertEquals(mov.getEquipmentCondition().getPhysicalConditionCode(), movementDto.getConditionCode(), "Condition code should be mapped correctly");
    }
    @Test
    public void MapsCorrectlyUpdatedMovementTest(){
        //arrange
        Equipment eq = EquipmentFactory.CreateEquipment();
        eq.setId(1);
        Movement mov = MovementFactory.CreateMovementEntity(eq);
        mov.setId(1);
        MovementDto testMovementDto = mapper.MapToMovementDto(mov);

        //act
        Movement newMove = mapper.MapToMovementEntity(testMovementDto);

        //assert
        assertEquals(newMove.getId(), mov.getId(), "Movement ID should match");
        assertEquals(newMove.getEquipment().getId(), mov.getEquipment().getId(), "equipment ID from movement should match");
        assertEquals(newMove.getEquipment().getPrefix(), mov.getEquipment().getPrefix(), "equipment prefix from movement should match");

    }
    @Test
    public void MapsCorrectlyMovementDtoToEntityTest() {
        //arrange
        Equipment testEquipment = EquipmentFactory.CreateEquipment();
        testEquipment.setId(1);
        Movement testMovement = MovementFactory.CreateMovementEntity(testEquipment);
        testMovement.setId(1);
        MovementDto testMovementDto = mapper.MapToMovementDto(testMovement);

        String oldPrefix = testMovement.getEquipment().getPrefix();
        String oldEquipmentTypeCode = testMovement.getEquipment().getEquipmentType().getEquipmentTypeCode();
        String oldMovementTypeCode = testMovement.getMovementType().getMovementTypeCode();
        String oldConditionCode = testMovement.getEquipmentCondition().getPhysicalConditionCode();

        //act
        testMovementDto.setPrefix("AAA");
        testMovementDto.setEquipmentTypeCode("Test");
        testMovementDto.setMovementCode("AAA");
        testMovementDto.setConditionCode("AAA");
        Movement updatedMovement = mapper.ConvertMovementDtoToEntity(testMovementDto,testMovement);

        //assert
        assertNotNull(updatedMovement);
        assertNotEquals(oldPrefix, updatedMovement.getEquipment().getPrefix());
        assertNotEquals(oldEquipmentTypeCode, updatedMovement.getEquipment().getEquipmentType().getEquipmentTypeCode());
        assertNotEquals(oldMovementTypeCode, updatedMovement.getMovementType().getMovementTypeCode());
        assertNotEquals(oldConditionCode, updatedMovement.getEquipmentCondition().getPhysicalConditionCode());

        assertEquals(testMovementDto.getPrefix(), updatedMovement.getEquipment().getPrefix());
        assertEquals(testMovementDto.getEquipmentTypeCode(), updatedMovement.getEquipment().getEquipmentType().getEquipmentTypeCode());
        assertEquals(testMovementDto.getMovementCode(), updatedMovement.getMovementType().getMovementTypeCode());
        assertEquals(testMovementDto.getConditionCode(), updatedMovement.getEquipmentCondition().getPhysicalConditionCode());

    }
}
