package com.DEVLOP.UnitTests.Commands;

import com.DEVLOP.Application.Commands.EquipmentCommand;
import com.DEVLOP.Application.DTOS.EquipmentDto;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.concurrent.CompletionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

/*
todo:
refactor nomenclatura e qualidade do teste. Cobrir edge cases.
 */
public class EquipmentCommandTest {

    @Mock
    private EquipmentRepository equipmentRepository;
    @Mock
    private IEquipmentMapper mapper;
    @InjectMocks
    private EquipmentCommand equipmentCommand;
    private Equipment existingEquipment;
    private EquipmentDto equipmentDTO;
    private Equipment updatedEquipment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        existingEquipment = EquipmentFactory.CreateEquipment();
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
        when(equipmentRepository.PersistEquipment(updatedEquipment)).thenReturn(updatedEquipment);

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
        verify(equipmentRepository, times(1)).PersistEquipment(updatedEquipment);
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
