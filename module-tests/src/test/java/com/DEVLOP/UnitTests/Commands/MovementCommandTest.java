package com.DEVLOP.UnitTests.Commands;

import com.DEVLOP.Application.Commands.MovementCommand;
import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.MovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class MovementCommandTest {
    @Mock
    private MovementRepository movementRepository;
    @Mock
    private IMovementMapper mapper;

    @InjectMocks
    private MovementCommand movementCommand;

    private Movement testMovement;
    private Equipment testEquipment;
    private MovementDto testMovementDto;
    private MovementDto updatedMovementDto;
    private Movement updatedMovement;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);

        testEquipment = EquipmentFactory.CreateEquipment();
        testEquipment.setId(1);

        testMovement = MovementFactory.CreateMovementEntity(testEquipment);
        testMovement.setId(2);

        testMovementDto = new MovementDto();
        testMovementDto.setId(2);
        testMovementDto.setEquipmentId(1); // Make sure it matches testEquipment

        updatedMovementDto = new MovementDto();
        updatedMovementDto.setId(2);
        updatedMovementDto.setEquipmentId(1); // Ensure consistency

        updatedMovement = new Movement();
        updatedMovement.setId(2);


    }

    @Test
    public void MovementUpdateTestSuccess(){
        //arrange
        when(movementRepository.FindMovementById(2)).thenReturn(Optional.of(testMovement));
        when(mapper.UpdateMovementEntity(updatedMovementDto, testMovement))
                .thenReturn(updatedMovement);
        when(movementRepository.UpdateMovement(updatedMovement)).thenReturn(updatedMovement);
        // Act
        CompletableFuture<Movement> futureResult = movementCommand.UpdateMovementAsync(testMovement.getId(), updatedMovementDto);
        Movement result = futureResult.join(); // Get the async result

        // Assert
        assertNotNull(result, "Updated movement should not be null");
        assertEquals(updatedMovement.getId(), result.getId(), "Updated movement ID should match");

        // Verify interactions
        verify(movementRepository, times(1)).FindMovementById(2);
        verify(mapper, times(1)).UpdateMovementEntity(updatedMovementDto, testMovement);
        verify(movementRepository, times(1)).UpdateMovement(updatedMovement);
    }
}
