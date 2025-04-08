package com.DEVLOP.UnitTests.Commands;

import com.DEVLOP.Application.Commands.MovementCommand;
import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovementCommandTest {
    @Mock
    private MovementRepository movementRepository;
    @Mock
    private IMovementMapper mapper;

    @InjectMocks
    private MovementCommand movementCommand;

    private Equipment mockEquipment;

    private Movement mockMovement;
    private Movement mockMovement2;
    private Movement mockMovement3;
    private Movement mockMovement4;


    private MovementDto mockMovementDto;
    private MovementDto mockMovementDto2;
    private MovementDto mockMovementDto3;
    private MovementDto mockMovementDto4;

    private MovementDto mockUpdatedMovementDto;
    private MovementDto mockUpdatedMovementDto2;
    private MovementDto mockUpdatedMovementDto3;
    private MovementDto mockUpdatedMovementDto4;

    private Movement mockUpdatedMovement;

    private List<Movement> mockMovementList;
    private List<MovementDto> mockMovementDtoList;
    private List<MovementDto> mockUpdatedMovementDtoList;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);
        //mock equipment
        mockEquipment = EquipmentFactory.CreateEquipment();
        mockEquipment.setId(1);

        //mock movements
        mockMovement = MovementFactory.CreateMovement(mockEquipment);
        mockMovement2 = MovementFactory.CreateMovement(mockEquipment);
        mockMovement3 = MovementFactory.CreateMovement(mockEquipment);
        mockMovement4 = MovementFactory.CreateMovement(mockEquipment);

        //set mock movements ID
        mockMovement.setId(1);
        mockMovement2.setId(2);
        mockMovement3.setId(3);
        mockMovement4.setId(4);


        //mock movement list
        mockMovementList = new ArrayList<>();
        mockMovementList.add(mockMovement2);
        mockMovementList.add(mockMovement3);
        mockMovementList.add(mockMovement4);


        //mock movement dtos to test mapping
        mockMovementDto = new MovementDto();
        mockMovementDto2 = new MovementDto();
        mockMovementDto3 = new MovementDto();
        mockMovementDto4 = new MovementDto();

        mockMovementDto.setId(1);
        mockMovementDto2.setId(2);
        mockMovementDto3.setId(3);
        mockMovementDto4.setId(4);

        mockMovementDto.setEquipmentId(1);
        mockMovementDto2.setEquipmentId(1);
        mockMovementDto3.setEquipmentId(1);
        mockMovementDto4.setEquipmentId(1);
        //mock movement dto list
        mockMovementDtoList = new ArrayList<>();
        mockMovementDtoList.add(mockMovementDto2);
        mockMovementDtoList.add(mockMovementDto3);
        mockMovementDtoList.add(mockMovementDto4);




        //mock updated movements DTO
        mockUpdatedMovementDto = new MovementDto();
        mockUpdatedMovementDto2 = new MovementDto();
        mockUpdatedMovementDto3 = new MovementDto();
        mockUpdatedMovementDto4 = new MovementDto();

        mockUpdatedMovementDto.setId(1);
        mockUpdatedMovementDto2.setId(2);
        mockUpdatedMovementDto3.setId(3);
        mockUpdatedMovementDto4.setId(4);

        mockUpdatedMovementDto.setEquipmentId(1);
        mockUpdatedMovementDto2.setEquipmentId(1);
        mockUpdatedMovementDto3.setEquipmentId(1);
        mockUpdatedMovementDto4.setEquipmentId(1);

        //mock updated dto list
        mockUpdatedMovementDtoList = new ArrayList<>();
        mockUpdatedMovementDtoList.add(mockUpdatedMovementDto2);
        mockUpdatedMovementDtoList.add(mockUpdatedMovementDto3);
        mockUpdatedMovementDtoList.add(mockUpdatedMovementDto4);
        //------------------//
        mockUpdatedMovement = new Movement();
        mockUpdatedMovement.setId(2);
    }
    @Test
    public void MovementUpdateTestSuccess(){
        //arrange
        when(movementRepository.FindMovementById(2)).thenReturn(Optional.of(mockMovement));
        when(mapper.UpdateMovementEntity(mockUpdatedMovementDto, mockMovement))
                .thenReturn(mockUpdatedMovement);
        when(movementRepository.UpdateMovement(mockUpdatedMovement)).thenReturn(mockUpdatedMovement);
        // Act
        CompletableFuture<Movement> futureResult = movementCommand.UpdateMovementAsync(mockMovement.getId(), mockUpdatedMovementDto);
        Movement result = futureResult.join(); // Get the async result

        // Assert
        assertNotNull(result, "Updated movement should not be null");
        assertEquals(mockUpdatedMovement.getId(), result.getId(), "Updated movement ID should match");

        // Verify interactions
        verify(movementRepository, times(1)).FindMovementById(2);
        verify(mapper, times(1)).UpdateMovementEntity(mockUpdatedMovementDto, mockMovement);
        verify(movementRepository, times(1)).UpdateMovement(mockUpdatedMovement);
    }

    @Test
    public void GroupMovementChangedTestSuccess(){
        //arrange
        List<Integer> idList = List.of(2,3,4);
        when(movementRepository.ReturnMovementsByIDList(idList)).thenReturn(mockMovementList);
        // Mock each movement update
        // Mock the mapper behavior (returning updated movements)
        when(mapper.UpdateMovementEntity(any(MovementDto.class), any(Movement.class)))
                .thenAnswer(invocation -> invocation.getArgument(1)); // Simply return the same movement, simulating an update

        // Mock repository save (returning the updated movement list)
        when(movementRepository.SaveMovementList(anyList())).thenReturn("Movement list has been saved");

        // Mock DTO mapping after update
        when(mapper.MapToMovementDto(any(Movement.class)))
                .thenReturn(mockUpdatedMovementDto, mockUpdatedMovementDto2, mockUpdatedMovementDto3);

        // Act
        CompletableFuture<List<MovementDto>> futureResult = movementCommand.ChangeGroupMovement(idList, mockUpdatedMovementDto);
        List<MovementDto> result = futureResult.join(); // Wait for async operation to complete

        // Assert
        assertNotNull(result, "Updated movement list should not be null");
        assertEquals(3, result.size(), "List should contain three updated movements");

        // Verify interactions (ensuring calls were made)
        verify(movementRepository, times(1)).ReturnMovementsByIDList(idList); //chamar a lista
        verify(mapper, times(3)).UpdateMovementEntity(any(MovementDto.class), any(Movement.class)); //faz update aos elementos na lista
        verify(movementRepository, times(1)).SaveMovementList(anyList());
        verify(mapper, times(3)).MapToMovementDto(any(Movement.class));
    }
    @Test
    public void ChangeGroupMovementTestException(){
        // Arrange
        List<Integer> invalidIdList = List.of(99, 100, 101); // IDs that do not exist

        // Mock repository to return an empty list (no movements found)
        when(movementRepository.ReturnMovementsByIDList(invalidIdList)).thenReturn(new ArrayList<>());

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementCommand.ChangeGroupMovement(invalidIdList, mockMovementDto);

        // Verify exception is thrown
        assertThrows(CompletionException.class, () -> futureResult.join(), "Expected CompletionException due to missing movements");

        // Verify MovementNotFoundException is the cause
        try {
            futureResult.join();
        } catch (CompletionException e) {
            assertTrue(e.getCause() instanceof MovementNotFoundException, "Cause should be MovementNotFoundException");
        }

        // Verify interactions
        verify(movementRepository, times(1)).ReturnMovementsByIDList(invalidIdList);
        verifyNoInteractions(mapper); // Mapper should not be called if movements are not found
    }
}
