package com.DEVLOP.UnitTests.Queries;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapperImpl;
import com.DEVLOP.Application.Queries.MovementQuery;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.EquipmentRepo;
import com.DEVLOP.Repositories.MovementRepo;
import com.DEVLOP.Specifications.MovementSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
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
    private MovementRepo movementRepo;
    @Mock
    private EquipmentRepo equipmentRepo;
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

    @Test
    public void ReturnFilteredMovementListAsyncTest() throws ExecutionException, InterruptedException {
        //arrange
        Map<String, Object> filter = new HashMap<>();
        filter.put("equipment.prefix", testEquipment.getPrefix());
        Specification<Movement> mockSpec = mock(Specification.class);

        when(movementSpecification.BuildSpecification(filter)).thenReturn(mockSpec);
        when(movementRepo.ReturnFilteredMovementList(mockSpec)).thenReturn(testMovementList);
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
        verify(movementRepo, times(1)).ReturnFilteredMovementList(mockSpec);
        verify(mapper, times(testMovementList.size())).MapToMovementDto(any(Movement.class));
    }

    @Test
    public void ReturnEquipmentMovementAsyncTestSuccess(){
        //arrange
        // Mock dependencies
        when(equipmentRepo.FindByID(1)).thenReturn(Optional.of(testEquipment));
        when(movementRepo.GetMovementsOfEquipment(testEquipment))
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
        verify(equipmentRepo, times(1)).FindByID(1);
        verify(movementRepo, times(1)).GetMovementsOfEquipment(testEquipment);
        verify(mapper, times(1)).MapToMovementDto(testMovement);
    }

    @Test
    public void ReturnEquipmentMovementAsyncTest_EquipmentNotFound() {
        // Arrange: Equipment ID does not exist
        when(equipmentRepo.FindByID(999)).thenReturn(Optional.empty());

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(999);

        CompletionException thrown = assertThrows(CompletionException.class, futureResult::join,
                "Should throw ExecutionException wrapping EquipmentNotFoundException");

        assertTrue(thrown.getCause() instanceof EquipmentNotFoundException,
                "Cause should be EquipmentNotFoundException");

        assertEquals("No equipment found with ID 999", thrown.getCause().getMessage());

        verify(equipmentRepo, times(1)).FindByID(999);
        verifyNoInteractions(movementRepo, mapper);
    }

    @Test
    public void ReturnEquipmentMovementAsyncTest_NoMovementsFound() {
        // Arrange: Equipment exists but has no movements
        when(equipmentRepo.FindByID(2)).thenReturn(Optional.of(testEquipment));
        when(movementRepo.GetMovementsOfEquipment(testEquipment)).thenReturn(Collections.emptyList());

        // Act
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(2);
        List<MovementDto> result = futureResult.join(); // Get async result

        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be an empty list");

        // Verify interactions
        verify(equipmentRepo, times(1)).FindByID(2);
        verify(movementRepo, times(1)).GetMovementsOfEquipment(testEquipment);
        verifyNoInteractions(mapper);
    }

    @Test
    public void ReturnEquipmentMovementAsyncTest_UnexpectedError() {
        // Arrange: Simulate error
        when(equipmentRepo.FindByID(3)).thenReturn(Optional.of(testEquipment));
        when(movementRepo.GetMovementsOfEquipment(testEquipment)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnMovementListFromEquipAsync(3);

        CompletionException thrown = assertThrows(CompletionException.class, futureResult::join,
                "Should throw ExecutionException wrapping RuntimeException");

        assertInstanceOf(RuntimeException.class, thrown.getCause(), "Cause should be RuntimeException");

        assertEquals("Unexpected error retrieving movements", thrown.getCause().getMessage());

        // Verify interactions
        verify(equipmentRepo, times(1)).FindByID(3);
        verify(movementRepo, times(1)).GetMovementsOfEquipment(testEquipment);
    }
    @Test
    public void ReturnsMovementByIdAsyncTestSuccess(){
        //arrange
        when(movementRepo.FindMovementById(2)).thenReturn(Optional.of(testMovement));
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
        verify(movementRepo, times(1)).FindMovementById(2);
        verify(mapper, times(1)).MapToMovementDto(testMovement);

    }


    @Test
    public void ReturnRangeFilteredMovementListAsyncTest() throws ExecutionException, InterruptedException {
        // Arrange
        LocalDate fromDate = LocalDate.of(2024, 1, 1);
        LocalDate toDate = LocalDate.of(2024, 12, 31);

        Map<String, Object> fromFilter = Map.of("date", fromDate);
        Map<String, Object> toFilter = Map.of("date", toDate);

        Specification<Movement> mockSpec = mock(Specification.class);
        Movement mockMovement = new Movement();
        mockMovement.setId(1);
        MovementDto mockDto = new MovementDto();
        mockDto.setId(1);

        when(movementSpecification.SpecificationBetween(fromFilter, toFilter)).thenReturn(mockSpec);
        when(movementRepo.ReturnFilteredMovementList(mockSpec)).thenReturn(List.of(mockMovement));
        when(mapper.MapToMovementDto(mockMovement)).thenReturn(mockDto);

        // Act
        CompletableFuture<List<MovementDto>> futureResult = movementQuery.ReturnRangeFilteredMovementListAsync(fromFilter, toFilter);
        List<MovementDto> result = futureResult.get();

        // Assert
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());

        verify(movementSpecification).SpecificationBetween(fromFilter, toFilter);
        verify(movementRepo).ReturnFilteredMovementList(mockSpec);
        verify(mapper).MapToMovementDto(mockMovement);

    }
    @Test
    void testReturnRangeFilteredMovementListAsync_withNullFilters_shouldThrowIllegalArgumentException() {
        CompletableFuture<List<MovementDto>> future = movementQuery.ReturnRangeFilteredMovementListAsync(null, null);

        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("Date filter maps must not be null.", exception.getCause().getMessage());
    }
    @Test
    void testReturnRangeFilteredMovementListAsync_withNoResults_shouldThrowMovementNotFoundException() {
        Map<String, Object> fromFilter = Map.of("date", LocalDate.of(2023, 1, 1));
        Map<String, Object> toFilter = Map.of("date", LocalDate.of(2023, 12, 31));

        Specification<Movement> mockSpec = mock(Specification.class);
        when(movementSpecification.SpecificationBetween(fromFilter, toFilter)).thenReturn(mockSpec);
        when(movementRepo.ReturnFilteredMovementList(mockSpec)).thenReturn(List.of());

        CompletableFuture<List<MovementDto>> future = movementQuery.ReturnRangeFilteredMovementListAsync(fromFilter, toFilter);

        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertTrue(exception.getCause() instanceof MovementNotFoundException);
        assertEquals("No matches for that query.", exception.getCause().getMessage());
    }
}

