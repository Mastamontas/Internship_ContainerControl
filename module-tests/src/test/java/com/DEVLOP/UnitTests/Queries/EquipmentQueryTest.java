package com.DEVLOP.UnitTests.Queries;
import com.DEVLOP.Application.Mappers.IEquipmentMapper;
import com.DEVLOP.Application.DTOS.EquipmentDTO;
import com.DEVLOP.Application.Queries.EquipmentQuery;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
     * Test for the {@link EquipmentQuery#fetchAndMapEquipments()} method.
     * This test verifies that the method returns a valid list of DTOs by
     * converting a list of mock equipment entities using the mapper.
     * It checks that the result is not null and the correct number of items is returned.
     */
    @Test//return correct equipment list and dto
    void fetchAndMapEquipmentsReturnsValidDTOList (){
        int runTimes = faker.number().numberBetween(5,100);
        // Create a mock list of Equipment
        List<Equipment> mockEquipmentList = generateMockEquipment(runTimes);

        // Stub the repository to return the mock list
        when(equipmentRepository.findAll()).thenReturn(mockEquipmentList);

        // Stub the mapper to convert Equipment to DTO
        when(mapper.toDTO(any(Equipment.class)))
                .thenAnswer(invocation -> mapToMockDTO(invocation.getArgument(0)));

        // Call the method under test
        List<EquipmentDTO> result = equipmentQuery.fetchAndMapEquipments();
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
    void fetchAndMapEquipmentsObjectNotFound(){
        // Mock the repository to return an empty list
        when(equipmentRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method under test
        List<EquipmentDTO> result = equipmentQuery.fetchAndMapEquipments();

        // Assert that the result is an empty list
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result list should be empty");
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
    //private map to DTO
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
