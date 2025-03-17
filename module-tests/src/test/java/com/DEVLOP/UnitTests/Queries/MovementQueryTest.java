package com.DEVLOP.UnitTests.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.Application.Queries.MovementQuery;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovementQueryTest {
    /*
    mockar dependencias
    depende do objeto vindo da persistence depende do mapper
    teste ao que retorna


     */
    @Mock
    private IMovementMapperImpl mapper;
    @Mock
    private MovementRepository movementRepository;
    @Mock
    EquipmentRepository equipmentRepository;
    @InjectMocks
    private MovementQuery movementQuery;

    Equipment testEquipment = EquipmentFactory.CreateEquipment();//create equipment entity
    Movement testMovement = MovementFactory.CreateMovementEntity(testEquipment);
    MovementDto testMovementDto = new MovementDto();

    @BeforeEach
    public void SetUp(){

        MockitoAnnotations.openMocks(this);
        testEquipment.setId(1);
        testMovement.setId(2);
        testMovement.setEquipment(testEquipment);//pensar nesta relacao
        testMovementDto.setId(2);
    }

    @Test
    public void ReturnEquipmentMovementAsyncTestSuccess(){
        //arrange
        // Mock dependencies
        when(equipmentRepository.FindByID(1)).thenReturn(Optional.of(testEquipment));
        when(movementRepository.GetMovementsOfEquipment(testEquipment))
                .thenReturn(Arrays.asList(testMovement));
        when(mapper.MapToMovementDto(testMovement)).thenReturn(testMovementDto);

        //act
        // Call the method
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnEquipmentMovementsAsync(1);
        // Assert the result
        List<MovementDto> result = futureResult.join(); // Get the async result

        assertNotNull(result, "Result should not be null");
        assertEquals(1, result.size(), "List should contain 1 MovementDto");
        assertEquals(testMovementDto, result.get(0), "Returned MovementDto should match the mapped DTO");
        // Verify interactions
        verify(equipmentRepository, times(1)).FindByID(1);
        verify(movementRepository, times(1)).GetMovementsOfEquipment(testEquipment);
        verify(mapper, times(1)).MapToMovementDto(testMovement);
    }

    //todo - more tests
    @Test
    public void ReturnEquipmentMovementAsyncTest_EquipmentNotFound() {
        // Arrange: Equipment ID does not exist
        when(equipmentRepository.FindByID(999)).thenReturn(Optional.empty());

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnEquipmentMovementsAsync(999);

        CompletionException thrown = assertThrows(CompletionException.class, futureResult::join,
                "Should throw ExecutionException wrapping EquipmentNotFoundException");

        assertTrue(thrown.getCause() instanceof EquipmentNotFoundException,
                "Cause should be EquipmentNotFoundException");

        assertEquals("No equipment found with ID 999", thrown.getCause().getMessage());

        verify(equipmentRepository, times(1)).FindByID(999);
        verifyNoInteractions(movementRepository, mapper);
    }

    @Test
    public void ReturnEquipmentMovementAsyncTest_NoMovementsFound() {
        // Arrange: Equipment exists but has no movements
        when(equipmentRepository.FindByID(2)).thenReturn(Optional.of(testEquipment));
        when(movementRepository.GetMovementsOfEquipment(testEquipment)).thenReturn(Collections.emptyList());

        // Act
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnEquipmentMovementsAsync(2);
        List<MovementDto> result = futureResult.join(); // Get async result

        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be an empty list");

        // Verify interactions
        verify(equipmentRepository, times(1)).FindByID(2);
        verify(movementRepository, times(1)).GetMovementsOfEquipment(testEquipment);
        verifyNoInteractions(mapper);
    }

    @Test
    public void ReturnEquipmentMovementAsyncTest_UnexpectedError() {
        // Arrange: Simulate an unexpected error
        when(equipmentRepository.FindByID(3)).thenReturn(Optional.of(testEquipment));
        when(movementRepository.GetMovementsOfEquipment(testEquipment)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnEquipmentMovementsAsync(3);

        CompletionException thrown = assertThrows(CompletionException.class, futureResult::join,
                "Should throw ExecutionException wrapping RuntimeException");

        assertInstanceOf(RuntimeException.class, thrown.getCause(), "Cause should be RuntimeException");

        assertEquals("Unexpected error retrieving movements", thrown.getCause().getMessage());

        // Verify interactions
        verify(equipmentRepository, times(1)).FindByID(3);
        verify(movementRepository, times(1)).GetMovementsOfEquipment(testEquipment);
    }
}

