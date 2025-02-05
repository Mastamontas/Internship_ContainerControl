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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
TODO
Refactor test names for corresponding classes
clean classes
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

        when(equipmentRepository.findAll()).thenReturn(mockEquipmentList);


        when(mapper.toDTO(any(Equipment.class)))
                .thenAnswer(invocation -> mapToMockDTO(invocation.getArgument(0)));

        CompletableFuture<List<EquipmentDTO>> futureList = equipmentQuery.findAllAsync();
        List<EquipmentDTO> result = futureList.join(); //join waits for completion and returns value
        for (EquipmentDTO e : result){
            System.out.println(e.toString());
        }

        // Assertions
        assertNotNull(result, "Result should not be null");
        assertEquals(runTimes, result.size(), "Result size should match mock data size");

        // Verify mapper and repository interactions
        verify(equipmentRepository, times(1)).findAll();
        verify(mapper, times(runTimes)).toDTO(any(Equipment.class));
    }

    /**
     * Tests null response for equipment fetch and map method
     * "when" method returns null but can return an empty array list
     *
     */
    @Test
    void findAllAsyncObjectNotFound(){
        // Mock the repository to return an empty list
        when(equipmentRepository.findAll()).thenReturn(new ArrayList<>());

        CompletableFuture<List<EquipmentDTO>> futureList = equipmentQuery.findAllAsync();
        List<EquipmentDTO> result = futureList.join(); //join waits for completion and returns value

        // Assert that the result is an empty list
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");
    }
    /**
     * Test for returning test by unique details
     * Test for when the query returns null, return the correct exception
     */
    @Test
    void returnEqDTOByUniqueDetailsAsync_WhenEquipmentExists_ShouldReturnDTO() throws ExecutionException, InterruptedException {
        // Generate a single mock Equipment
        List<Equipment> mockEquipments = generateMockEquipment(1);
        Equipment mockEquipment = mockEquipments.get(0);

        // Convert it to DTO
        EquipmentDTO mockEquipmentDTO = mapToMockDTO(mockEquipment);

        // Mock repository and mapper behavior
        when(equipmentRepository.findEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber()))
                .thenReturn(mockEquipment);
        when(mapper.toDTO(mockEquipment)).thenReturn(mockEquipmentDTO);

        // Call the method asynchronously
        CompletableFuture<EquipmentDTO> resultFuture = equipmentQuery.returnEqDTOByUniqueDetailsAsync(
                mockEquipment.getPrefix(),
                mockEquipment.getCheckDigit(),
                mockEquipment.getNumber()
        );
        EquipmentDTO result = resultFuture.get(); // Wait for completion

        // Assertions
        assertNotNull(result, "Result should not be null");
        assertEquals(mockEquipmentDTO.getPrefix(), result.getPrefix(), "Prefix should match");
        assertEquals(mockEquipmentDTO.getCheckDigit(), result.getCheckDigit(), "CheckDigit should match");
        assertEquals(mockEquipmentDTO.getNumber(), result.getNumber(), "Number should match");

        // Verify repository and mapper interactions
        verify(equipmentRepository, times(1)).findEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber());
        verify(mapper, times(1)).toDTO(mockEquipment);
    }

    @Test
    void returnEqDTOByUniqueDetailsAsync_WhenEquipmentNotFound_ShouldThrowException(){
        // Generate a mock Equipment but don’t return it from repository
        List<Equipment> mockEquipments = generateMockEquipment(1);
        Equipment mockEquipment = mockEquipments.get(0);

        // Mock repository to return null
        when(equipmentRepository.findEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber()))
                .thenReturn(null);

        // Call the method asynchronously
        CompletableFuture<EquipmentDTO> resultFuture = equipmentQuery.returnEqDTOByUniqueDetailsAsync(
                mockEquipment.getPrefix(),
                mockEquipment.getCheckDigit(),
                mockEquipment.getNumber()
        );

        // Assert exception
        ExecutionException thrown = assertThrows(ExecutionException.class, resultFuture::get);
        assertTrue(thrown.getCause() instanceof EquipmentNotFoundException, "Should throw EquipmentNotFoundException");

        // Verify repository was called but mapper was never used
        verify(equipmentRepository, times(1)).findEquipmentByUniqueDetails(mockEquipment.getPrefix(), mockEquipment.getCheckDigit(), mockEquipment.getNumber());
        verifyNoInteractions(mapper);
    }

    /**
     * Method for creation of a single equipment, for single or list test creation purposes
     * @param numberOfEqs
     * @return a list of equipments
     */
    private List<Equipment> generateMockEquipment(int numberOfEqs){
        List<Equipment> equipmentList = new ArrayList<>();
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

            equipmentType.setEquipmentClassID(equipmentClass);
            equipment.setEquipmentTypeID(equipmentType);

            equipmentList.add(equipment);
        }
        return equipmentList;
    }
    private EquipmentDTO mapToMockDTO(Equipment equipment) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setCheckDigit(equipment.getCheckDigit());
        dto.setNumber(equipment.getNumber());
        dto.setPrefix(equipment.getPrefix());
        dto.setGrossWeight(equipment.getGrossWeight());
        dto.setEquipmentTypeCode(equipment.getEquipmentTypeID().getEquipmentTypeCode());
        dto.setEquipmentTypeLength(equipment.getEquipmentTypeID().getEquipmentTypeLength());
        dto.setEquipmentTypeTareWeight(equipment.getEquipmentTypeID().getEquipmentTypeTareWeight());
        dto.setEquipmentClassCode(equipment.getEquipmentTypeID().getEquipmentClassID().getEquipmentClassCode());
        return dto;
    }
}
