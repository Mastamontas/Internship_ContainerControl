package com.DEVLOP.UnitTests.Commands;

import com.DEVLOP.Application.Commands.EquipmentCommand;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.EquipmentClass;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

/*
todo
substituir metodos generate equipment por metodos factory. apagar repetições de metodo criacao
nomenclatura metodos

 */
//@SpringBootTest
public class EquipmentCommandTest {
    @Mock
    private EquipmentRepository equipmentRepository;
    @Mock
    private IEquipmentMapper mapper;
    @InjectMocks
    private EquipmentCommand equipmentCommand;
    private Faker faker;
    private Equipment existingEquipment;
    private EquipmentDto equipmentDTO;
    private Equipment updatedEquipment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
        existingEquipment = generateMockEquipment(1).get(0);
        equipmentDTO = mapToMockDTO(existingEquipment);
        updatedEquipment = new Equipment();
        updatedEquipment.setId(existingEquipment.getId());
        updatedEquipment.setComment("Updated Equipment");

    }

    @Test
    void testUpdateEquipmentAsync_UpdatesEquipmentCorrectly(){
        // Arrange
        when(equipmentRepository.FindByID(existingEquipment.getId())).thenReturn(Optional.of(existingEquipment));
        when(mapper.MapAndUpdateEquipmentFromEquipmentDto(equipmentDTO, existingEquipment)).thenReturn(updatedEquipment);
        when(equipmentRepository.SaveEquipmentInDb(updatedEquipment)).thenReturn(updatedEquipment);

        // Act
        Equipment result = null;
        try {
            result = equipmentCommand.UpdateEquipment(existingEquipment.getId(), equipmentDTO).join();
        } catch (CompletionException e) {
            // Handle exceptions if necessary (e.g., rethrow, log, etc.)
            e.printStackTrace();
        }

        // Assert
        assertEquals(updatedEquipment, result);
        verify(equipmentRepository, times(1)).SaveEquipmentInDb(updatedEquipment);
    };

    @Test
    void testUpdateEquipmentAsync_ReturnsException(){
        // Arrange
        when(equipmentRepository.FindByID(existingEquipment.getId())).thenReturn(Optional.empty());

        // Act & Assert
        CompletionException exception = assertThrows(CompletionException.class, () -> {
            equipmentCommand.UpdateEquipment(existingEquipment.getId(), equipmentDTO).join();
        });

        // Verify the cause of the CompletionException
        assertEquals(EquipmentNotFoundException.class, exception.getCause().getClass());
    };

    /**
     * Method for creation of a single equipment, for single or list test creation purposes
     * @param numberOfEqs
     * @return a list of equipments
     */
    private List<Equipment> generateMockEquipment(int numberOfEqs){
        List<Equipment> equipmentList = new ArrayList<>();
        /*
        todo
        rever este loop porque pode ser feito de maneira diferente e menos taxativa
         */
        for (int i = 0; i< numberOfEqs; i++){
            Equipment equipment = new Equipment();
            equipment.setId(faker.number().numberBetween(1, 100));
            equipment.setPrefix(faker.lorem().characters(3).toUpperCase());
            equipment.setNumber(faker.number().numberBetween(1000, 9999));
            equipment.setCheckDigit(faker.number().numberBetween(10, 99));
            equipment.setGrossWeight(faker.number().randomDouble(2, 500, 2000));

            EquipmentType equipmentType = new EquipmentType();
            equipmentType.setEquipmentTypeCode(faker.code().isbn10());
            equipmentType.setEquipmentTypeLength(faker.number().randomDouble(1, 10, 20));
            equipmentType.setEquipmentTypeTareWeight(faker.number().randomDouble(1, 1000, 3000));

            EquipmentClass equipmentClass = new EquipmentClass();
            equipmentClass.setEquipmentClassCode(faker.lorem().characters(5).toUpperCase());

            equipmentType.setEquipmentClass(equipmentClass);
            equipment.setEquipmentType(equipmentType);

            equipmentList.add(equipment);
        }
        return equipmentList;
    }

    private EquipmentDto mapToMockDTO(Equipment equipment) {
        EquipmentDto dto = new EquipmentDto();
        dto.setId(equipment.getId());
        dto.setCheckDigit(equipment.getCheckDigit());
        dto.setNumber(equipment.getNumber());
        dto.setPrefix(equipment.getPrefix());
        dto.setGrossWeight(equipment.getGrossWeight());
        dto.setEquipmentTypeCode(equipment.getEquipmentType().getEquipmentTypeCode());
        dto.setEquipmentTypeLength(equipment.getEquipmentType().getEquipmentTypeLength());
        dto.setEquipmentTypeTareWeight(equipment.getEquipmentType().getEquipmentTypeTareWeight());
        dto.setEquipmentClassCode(equipment.getEquipmentType().getEquipmentClass().getEquipmentClassCode());
        return dto;
    }
}
