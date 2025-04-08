package com.DEVLOP.UnitTests.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.Application.Queries.MovementQuery;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import com.DEVLOP.Specifications.MovementSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class MovementQueryTest {
    @Mock
    private IMovementMapperImpl mapper;
    @Mock
    private MovementSpecification movementSpecification;
    @Mock
    private MovementRepository movementRepository;
    @Mock
    private EquipmentRepository equipmentRepository;
    @InjectMocks
    private MovementQuery movementQuery;

    Equipment testEquipment = EquipmentFactory.CreateEquipment();//create equipment entity
    Movement testMovement = MovementFactory.CreateMovement(testEquipment);
    MovementDto testMovementDto = new MovementDto();
    List<Equipment> testEquipmentList = EquipmentFactory.CreateEquipmentList(4);
    //so associada a este equipamento
    List<Movement> testMovementList = MovementFactory.CreateMovementList(testEquipment, 3);

    @BeforeEach
    public void SetUp(){

        MockitoAnnotations.openMocks(this);

        //criar lista de equipamentos e por cada elemento na lista criar uma lista de movimentos associada
        testEquipment.setId(1);
        testMovement.setId(2);
        testMovement.setEquipment(testEquipment);//pensar nesta relacao
        testMovementDto.setId(2);


    }

    //test if movement list is returned correctly
    //asserts function in the repository is called
    //assert it returns a list
    //method tested here should not really be async, as they interact directly with database
    @Test
    public void ReturnMovementListFromEquipmentAsyncTest() throws ExecutionException, InterruptedException {
        //arrange
        Map<String, Object> filter = new HashMap<>();
        filter.put("equipment.prefix", testEquipment.getPrefix());
        Specification<Movement> mockSpec = mock(Specification.class);

        when(movementSpecification.BuildSpecification(filter)).thenReturn(mockSpec);
        when(movementRepository.ReturnFilteredMovementList(mockSpec)).thenReturn(testMovementList);
        when(mapper.MapToMovementDto(any(Movement.class))).thenAnswer(invocationOnMock -> {
            Movement movement = invocationOnMock.getArgument(0);
            MovementDto movementDto = new MovementDto();
            movementDto.setPrefix(movement.getEquipment().getPrefix());
            return movementDto;

        });
        //act
        CompletableFuture<List<MovementDto>>  futureList = movementQuery.ReturnFilteredMovementListAsync(filter);
        List<MovementDto> resultList = futureList.get();

        //assert
        assertNotNull(resultList, "The result list should not be null");
        assertFalse(resultList.isEmpty(), "The result list should not be empty!");
        assertEquals(testMovementList.size(), resultList.size(), "Result list size should match test data size");
        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(testMovementList.get(i).getEquipment().getPrefix(), resultList.get(i).getPrefix(),
                    "Prefixes should match between entity and DTO");
        }
        verify(movementRepository, times(1)).ReturnFilteredMovementList(mockSpec);
        verify(mapper, times(testMovementList.size())).MapToMovementDto(any(Movement.class));
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
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(1);
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

    @Test
    public void ReturnEquipmentMovementAsyncTest_EquipmentNotFound() {
        // Arrange: Equipment ID does not exist
        when(equipmentRepository.FindByID(999)).thenReturn(Optional.empty());

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(999);

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
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(2);
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
        // Arrange: Simulate error
        when(equipmentRepository.FindByID(3)).thenReturn(Optional.of(testEquipment));
        when(movementRepository.GetMovementsOfEquipment(testEquipment)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(3);

        CompletionException thrown = assertThrows(CompletionException.class, futureResult::join,
                "Should throw ExecutionException wrapping RuntimeException");

        assertInstanceOf(RuntimeException.class, thrown.getCause(), "Cause should be RuntimeException");

        assertEquals("Unexpected error retrieving movements", thrown.getCause().getMessage());

        // Verify interactions
        verify(equipmentRepository, times(1)).FindByID(3);
        verify(movementRepository, times(1)).GetMovementsOfEquipment(testEquipment);
    }
    @Test
    public void ReturnsMovementByIdAsyncTestSuccess(){
        //arrange
        when(movementRepository.FindMovementById(2)).thenReturn(Optional.of(testMovement));
        when(mapper.MapToMovementDto(testMovement)).thenReturn(testMovementDto);

        //act and assert
        CompletableFuture<MovementDto> futureResult = movementQuery.ReturnMovementById(2);
        MovementDto result = futureResult.join();

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(testMovementDto, result, "Returned MovementDto should match the mapped DTO");

        // Validate fields (assuming MovementDto has fields like id, name, etc.)
        assertEquals(testMovementDto.getId(), result.getId(), "ID should match the expected DTO");
        assertEquals(testMovementDto.getPrefix(), result.getPrefix(), "Name should match the expected DTO");
        assertEquals(testMovementDto.getDate(), result.getDate(), "Date should match the expected DTO");

        // Verify interactions
        verify(movementRepository, times(1)).FindMovementById(2);
        verify(mapper, times(1)).MapToMovementDto(testMovement);

    }
}

