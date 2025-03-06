package com.DEVLOP.UnitTests.Queries;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Application.Queries.EquipmentQuery;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
TODO
tests to Pascal Case
*/

/**
 * Unit test class for testing the {@link EquipmentQuery} class.
 * This class tests the fetch and map functionality of the EquipmentQuery,
 * ensuring that it properly interacts with the repository and mapper.
 */
public class EquipmentQueryTest {
    @Mock
    private EquipmentRepository equipmentRepository;
    @Mock
    private IEquipmentMapper mapper;

    @InjectMocks
    private EquipmentQuery equipmentQuery;
    private Faker faker;

    /**
     * Set up the necessary mocks and test data before each test.
     * Initializes the Faker instance and mocks the repository and mapper.
     */
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        faker = new Faker();
    }

    /**
     * This test verifies that the method returns a valid list of DTOs by
     * converting a list of mock equipment entities using the mapper.
     * It checks that the result is not null and the correct number of items is returned.
     */
    @Test//return correct equipment list and dto
    void findAllAsyncReturnsValidDTOList (){
        int runTimes = faker.number().numberBetween(1,10);
        // Create a mock list of Equipment
        List<Equipment> mockEquipmentList = generateMockEquipment(runTimes);

        when(equipmentRepository.FindAll()).thenReturn(mockEquipmentList);
        when(mapper.MaptoEquipmentDto(any(Equipment.class)))
                .thenAnswer(invocation -> mapToMockDTO(invocation.getArgument(0)));

        CompletableFuture<List<EquipmentDTO>> futureList = equipmentQuery.FindAllEquipmentsAsync();
        List<EquipmentDTO> result = futureList.join();

        assertNotNull(result, "Result should not be null");
        assertEquals(runTimes, result.size(), "Result size should match mock data size");

        // Verify mapper and repository interactions
        verify(equipmentRepository, times(1)).FindAll();
        verify(mapper, times(runTimes)).MaptoEquipmentDto(any(Equipment.class));
    }

    /**
     * Tests null response for equipment fetch and map method
     * "when" method returns null but can return an empty array list
     *
     */
    @Test
    void findAllAsyncObjectNotFound(){
        // Mock the repository to return an empty list
        when(equipmentRepository.FindAll()).thenReturn(new ArrayList<>());

        CompletableFuture<List<EquipmentDTO>> futureList = equipmentQuery.FindAllEquipmentsAsync();
        List<EquipmentDTO> result = futureList.join(); //join waits for completion and returns value

        // Assert that the result is an empty list
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");
    }
    /**
     * Test for returning test by unique details
     * Test for when the query returns null, return the correct exception
     */
  /*  @Test
    void returnEqDTOByUniqueDetailsAsync_WhenEquipmentExists_ShouldReturnDTO() throws ExecutionException, InterruptedException {
        // Generate a single mock Equipment
        List<Equipment> mockEquipments = generateMockEquipment(1);
        Equipment mockEquipment = mockEquipments.get(0);

        // Convert it to DTO
        EquipmentDTO mockEquipmentDTO = mapToMockDTO(mockEquipment);

        // Mock repository and mapper behavior
        when(equipmentRepository.FindEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber()))
                .thenReturn(Optional.of(mockEquipment));
        when(mapper.MaptoEquipmentDto(mockEquipment)).thenReturn(mockEquipmentDTO);

        // Call the method asynchronously
        CompletableFuture<EquipmentDTO> resultFuture = equipmentQuery.ReturnEqDTOByUniqueDetailsAsync(
                mockEquipment.getPrefix(),
                mockEquipment.getCheckDigit(),
                mockEquipment.getNumber()
        );
        EquipmentDTO result = resultFuture.get();

        // Assertions
        assertNotNull(result, "Result should not be null");
        assertEquals(mockEquipmentDTO.getPrefix(), result.getPrefix(), "Prefix should match");
        assertEquals(mockEquipmentDTO.getCheckDigit(), result.getCheckDigit(), "CheckDigit should match");
        assertEquals(mockEquipmentDTO.getNumber(), result.getNumber(), "Number should match");

        // Verify repository and mapper interactions
        verify(equipmentRepository, times(1)).FindEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber());
        verify(mapper, times(1)).MaptoEquipmentDto(mockEquipment);
    }*/

/*    @Test
    void returnEqDTOByUniqueDetailsAsync_WhenEquipmentNotFound_ShouldThrowException(){
        // Generate a mock Equipment but don’t return it from repository
        List<Equipment> mockEquipments = generateMockEquipment(1);
        Equipment mockEquipment = mockEquipments.get(0);

        // Mock repository to return null
        when(equipmentRepository.FindEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber()))
                .thenReturn(Optional.empty());

        // Call the method asynchronously
        CompletableFuture<EquipmentDTO> resultFuture = equipmentQuery.ReturnEqDTOByUniqueDetailsAsync(
                mockEquipment.getPrefix(),
                mockEquipment.getCheckDigit(),
                mockEquipment.getNumber()
        );

        // Assert exception
        ExecutionException thrown = assertThrows(ExecutionException.class, resultFuture::get);
        assertTrue(thrown.getCause() instanceof EquipmentNotFoundException, "Should throw EquipmentNotFoundException");

        // Verify repository was called but mapper was never used
        verify(equipmentRepository, times(1)).FindEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber());
        verifyNoInteractions(mapper);
    }*/


    @Test
    void getEquipmentByIDAsyncTest() throws ExecutionException, InterruptedException {
        Equipment mockEquipment = generateMockEquipment(1).get(0);
        when(equipmentRepository.FindByID(mockEquipment.getId())).thenReturn(Optional.of(mockEquipment));

        EquipmentDTO mockEquipmentDTO = mapToMockDTO(mockEquipment);
        when(mapper.MaptoEquipmentDto(mockEquipment)).thenReturn(mockEquipmentDTO);

        CompletableFuture<EquipmentDTO> future = equipmentQuery.GetEquipmentByIDAsync(mockEquipment.getId());
        EquipmentDTO result = future.get();

        assertNotNull(result, "Fetched equipment should not be null");
        assertEquals(mockEquipment.getId(), result.getId(), "Fetched equipment ID should match");
        assertEquals(mockEquipment.getPrefix(), result.getPrefix(), "Fetched equipment prefix should match");

        verify(equipmentRepository, times(1)).FindByID(mockEquipment.getId());
        verify(mapper, times(1)).MaptoEquipmentDto(mockEquipment);
    }
    @Test
    void getEquipmentByIDAsyncTest_ThrowsException(){
        int nonExistentId = 9999;  // ID that doesn't exist in the repository

        when(equipmentRepository.FindByID(nonExistentId)).thenReturn(Optional.empty());
        CompletableFuture<EquipmentDTO> future = equipmentQuery.GetEquipmentByIDAsync(nonExistentId);

        ExecutionException thrown = assertThrows(ExecutionException.class, future::get);
        assertTrue(thrown.getCause() instanceof EquipmentNotFoundException, "Expected EquipmentNotFoundException");
        verify(equipmentRepository, times(1)).FindByID(nonExistentId);
        verifyNoInteractions(mapper);
    }

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

    private EquipmentDTO mapToMockDTO(Equipment equipment) {
        EquipmentDTO dto = new EquipmentDTO();
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
