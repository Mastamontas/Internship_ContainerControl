package com.DEVLOP.UnitTests.Commands;

import com.DEVLOP.Application.Commands.MovementCommand;
import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.Equipment;
import com.DEVLOP.Repositories.Movement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovementCommandTest {
    @Mock
    private Movement movement;
    @Mock
    private IMovementMapper mapper;
    @Mock
    private Equipment equipment;

    @InjectMocks
    private MovementCommand movementCommand;

    private com.DEVLOP.Entities.Equipment mockEquipment;
    private com.DEVLOP.Entities.Equipment mockEquipment2;
    private com.DEVLOP.Entities.Equipment mockEquipment3;
    private com.DEVLOP.Entities.Equipment mockEquipment4;

    private com.DEVLOP.Entities.Movement mockMovement;
    private com.DEVLOP.Entities.Movement mockMovement2;
    private com.DEVLOP.Entities.Movement mockMovement3;
    private com.DEVLOP.Entities.Movement mockMovement4;


    private MovementDto mockMovementDto;
    private MovementDto mockMovementDto2;
    private MovementDto mockMovementDto3;
    private MovementDto mockMovementDto4;

    private MovementDto mockUpdatedMovementDto;
    private MovementDto mockUpdatedMovementDto2;
    private MovementDto mockUpdatedMovementDto3;
    private MovementDto mockUpdatedMovementDto4;

    private com.DEVLOP.Entities.Movement mockUpdatedMovement;
    private List<com.DEVLOP.Entities.Equipment> mockEquipmentList = new ArrayList<>();
    private List<com.DEVLOP.Entities.Movement> mockMovementList;
    private List<MovementDto> mockMovementDtoList;
    private List<MovementDto> mockUpdatedMovementDtoList;


    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.openMocks(this);
        //mock equipment
        mockEquipment = EquipmentFactory.CreateEquipment();
        mockEquipment.setId(1);
        //mock equipment list

        mockEquipment2 = EquipmentFactory.CreateEquipment();
        mockEquipment2.setId(2);
        mockEquipment3 = EquipmentFactory.CreateEquipment();
        mockEquipment3.setId(3);
        mockEquipment4 = EquipmentFactory.CreateEquipment();
        mockEquipment4.setId(4);

        //adicionar lista
        mockEquipmentList.add(mockEquipment2);
        mockEquipmentList.add(mockEquipment3);
        mockEquipmentList.add(mockEquipment4);


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
        mockUpdatedMovement = new com.DEVLOP.Entities.Movement();
        mockUpdatedMovement.setId(2);
    }
    @Test
    public void MovementUpdateTestSuccess(){
        //arrange
        when(movement.FindMovementById(1)).thenReturn(Optional.of(mockMovement));
        when(mapper.UpdateMovementEntity(mockUpdatedMovementDto, mockMovement))
                .thenReturn(mockUpdatedMovement);
        when(movement.UpdateMovement(mockUpdatedMovement)).thenReturn(mockUpdatedMovement);
        // Act
        CompletableFuture<com.DEVLOP.Entities.Movement> futureResult = movementCommand.UpdateMovementAsync(mockMovement.getId(), mockUpdatedMovementDto);
        com.DEVLOP.Entities.Movement result = futureResult.join(); // Get the async result

        // Assert
        assertNotNull(result, "Updated movement should not be null");
        assertEquals(mockUpdatedMovement.getId(), result.getId(), "Updated movement ID should match");

        // Verify interactions
        verify(movement, times(1)).FindMovementById(1);
        verify(mapper, times(1)).UpdateMovementEntity(mockUpdatedMovementDto, mockMovement);
        verify(movement, times(1)).UpdateMovement(mockUpdatedMovement);
    }
    @Test
    public void GroupMovementChangedTestSuccess(){
        //arrange
        List<Integer> idList = List.of(2,3,4);
        when(movement.ReturnMovementsByIDList(idList)).thenReturn(mockMovementList);
        // Mock each movement update
        // Mock the mapper behavior (returning updated movements)
        when(mapper.UpdateMovementEntity(any(MovementDto.class), any(com.DEVLOP.Entities.Movement.class)))
                .thenAnswer(invocation -> invocation.getArgument(1)); // Simply return the same movement, simulating an update

        // Mock repository save (returning the updated movement list)
        when(movement.SaveMovementList(anyList())).thenReturn("Movement list has been saved");

        // Mock DTO mapping after update
        when(mapper.MapToMovementDto(any(com.DEVLOP.Entities.Movement.class)))
                .thenReturn(mockUpdatedMovementDto, mockUpdatedMovementDto2, mockUpdatedMovementDto3);

        // Act
        CompletableFuture<List<MovementDto>> futureResult = movementCommand.ChangeGroupMovement(idList, mockUpdatedMovementDto);
        List<MovementDto> result = futureResult.join(); // Wait for async operation to complete

        // Assert
        assertNotNull(result, "Updated movement list should not be null");
        assertEquals(3, result.size(), "List should contain three updated movements");

        // Verify interactions (ensuring calls were made)
        verify(movement, times(1)).ReturnMovementsByIDList(idList); //chamar a lista
        verify(mapper, times(3)).UpdateMovementEntity(any(MovementDto.class), any(com.DEVLOP.Entities.Movement.class)); //faz update aos elementos na lista
        verify(movement, times(1)).SaveMovementList(anyList());
        verify(mapper, times(3)).MapToMovementDto(any(com.DEVLOP.Entities.Movement.class));
    }
    @Test
    public void ChangeGroupMovementTestException(){
        // Arrange
        List<Integer> invalidIdList = List.of(99, 100, 101); // IDs that do not exist

        // Mock repository to return an empty list (no movements found)
        when(movement.ReturnMovementsByIDList(invalidIdList)).thenReturn(new ArrayList<>());

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementCommand.ChangeGroupMovement(invalidIdList, mockMovementDto);

        // Verify exception is thrown
        assertThrows(CompletionException.class, futureResult::join, "Expected CompletionException due to missing movements");

        // Verify MovementNotFoundException is the cause
        try {
            futureResult.join();
        } catch (CompletionException e) {
            assertInstanceOf(MovementNotFoundException.class, e.getCause(), "Cause should be MovementNotFoundException");
        }

        // Verify interactions
        verify(movement, times(1)).ReturnMovementsByIDList(invalidIdList);
        verifyNoInteractions(mapper); // Mapper should not be called if movements are not found
    }

    @Test
    public void AddGroupMovementTest() throws ExecutionException, InterruptedException {
        //arrange
        MovementDto movementDto = new MovementDto();
        movementDto.setComments("Movement to add an equipment to");

        com.DEVLOP.Entities.Movement movementToAddEquips = new com.DEVLOP.Entities.Movement();
        movementToAddEquips.setMovementComment("Movement to add an equipment to");
        List<Integer> idList =  List.of(2,3,4);

        when(equipment.GetEquipmentListFromID(idList)).thenReturn(mockEquipmentList);
        // Return a new instance each time it's mapped (clone to avoid object sharing)
        //todo study the thenAnswer to understand when and why it should be used
        when(mapper.MapToMovementEntity(movementDto)).thenAnswer(invocation -> {
            com.DEVLOP.Entities.Movement m = new com.DEVLOP.Entities.Movement();
            m.setMovementComment(movementDto.getComments());
            return m;
        });
        // Fake mapper back to DTO
        //todo study the thenAnswer to understand when and why it should be used
        when(mapper.MapToMovementDto(any(com.DEVLOP.Entities.Movement.class))).thenAnswer(invocation -> {
            com.DEVLOP.Entities.Movement m = invocation.getArgument(0);
            MovementDto dto = new MovementDto();
            dto.setComments(m.getMovementComment());
            return dto;
        });

        // Capture the list passed to repository
        ArgumentCaptor<List<com.DEVLOP.Entities.Movement>> movementCaptor = ArgumentCaptor.forClass((Class) List.class);
        when(movement.SaveMovementList(movementCaptor.capture()))
                .thenReturn("Movement list has been saved");

        // Act
        CompletableFuture<List<MovementDto>> resultFuture = movementCommand.AddMovementToEquipmentGroup(idList, movementDto);
        List<MovementDto> result = resultFuture.get(); // blocking call for test simplicity

        // Assert
        assertEquals(3, result.size());
        verify(equipment).GetEquipmentListFromID(idList);
        verify(movement).SaveMovementList(anyList());
        List<com.DEVLOP.Entities.Movement> savedMovements = movementCaptor.getValue();
        assertEquals(3, savedMovements.size());

        // Check that each movement has equipment set
        for (int i = 0; i < 3; i++) {
            assertNotNull(savedMovements.get(i).getEquipment());
            assertEquals("Movement to add an equipment to", savedMovements.get(i).getMovementComment());
        }

    }
    @Test
    public void AddGroupMovementTestException(){
        // Arrange
        MovementDto movementDto = new MovementDto();
        movementDto.setComments("This will fail");

        List<Integer> idList = List.of(99, 100); // doesn't matter since we're mocking the failure

        // Simulate an exception being thrown when trying to fetch equipment
        when(equipment.GetEquipmentListFromID(idList))
                .thenThrow(new RuntimeException("Database access error"));

        // Act & Assert
        ExecutionException exception = assertThrows(ExecutionException.class, () -> {
            movementCommand.AddMovementToEquipmentGroup(idList, movementDto).get();
        });

        Throwable cause = exception.getCause();
        assertTrue(cause instanceof EquipmentNotFoundException);
        assertEquals("cant create movements for selected equipments", cause.getMessage());
    }
}
