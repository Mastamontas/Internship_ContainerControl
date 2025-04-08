package com.DEVLOP.IntegrationTests.PersistenceTests;

import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
import com.DEVLOP.Specifications.MovementSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/*
todo feature add group movement
 */
@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovementRepositoryIntegrationTest {


    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private MovementSpecification movementSpecification;

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }private Equipment testEquipment;


    @BeforeEach
    public void SetUp(){
        testEquipment = EquipmentFactory.CreateEquipment();

        //mudar nome metodo para persistEquipment
        equipmentRepository.PersistEquipmentClass(testEquipment.getEquipmentType().getEquipmentClass());
        equipmentRepository.PersistEquipmentType(testEquipment.getEquipmentType());
        equipmentRepository.PersistEquipment(testEquipment);

        IntStream.rangeClosed(1,4).forEach(i->{
            Movement testMovement = MovementFactory.CreateMovement(testEquipment);
            movementRepository.PersistMovement(testMovement);
        });
    }
    @Test
    public void ReturnsListByDateOrder(){
        //setup ir buscar equipamento com id 3
        Equipment eq = equipmentRepository.FindAll().stream().findFirst().orElseThrow(()-> new EquipmentNotFoundException("no equip in test"));
        //act
        List<Movement> orderedMovementList = movementRepository.GetMovementsOfEquipment(eq);
        //assertions here
        for (Movement m : orderedMovementList){
            System.out.println(m.getDate());
            System.out.println(m.getEquipment().getId());
            System.out.println(m.getEquipment().getPrefix());
        }
    }
    @Test
    public void FindMovementByIDSuccessTest(){
        //arrange
        Movement mov1 = movementRepository.FindMovementById(1).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        Movement mov2 = movementRepository.FindMovementById(2).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        Movement mov3 = movementRepository.FindMovementById(3).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        Movement mov4 = movementRepository.FindMovementById(4).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));

        //act
        //assert
        assertNotNull(mov1);
        assertNotNull(mov2);
        assertNotNull(mov3);
        assertNotNull(mov4);
    }
    //todo improve this test
    @Test
    public void FindMovementByIDSuccessFail(){
        // Act & Assert - Check if the expected exception is thrown
        assertThrows(MovementNotFoundException.class, () -> {
            movementRepository.FindMovementById(5)
                    .orElseThrow(() -> new MovementNotFoundException("movement does not exist"));
        });
    }

    //todo clean test and improve assertions
    @Test
    public void ReturnAllMovementsFiltered(){
        //set up the spec
        Map<String, Object> filter1 = new HashMap<>();
        filter1.put("equipment.prefix",testEquipment.getPrefix());
        filter1.put("equipmentStatus.equipmentStatusCode", "IN_PROGRESS");
        Specification<Movement> spec =  movementSpecification.BuildSpecification(filter1);
        List<Movement> moveList1 = movementRepository.ReturnFilteredMovementList(spec);
        assertNotNull(moveList1);

    }

    @Test
    public void ReturnMovementListByIDListTest(){
        //arrange
        List<Integer> idList = List.of(1,2,3);
        //act
        List<Movement> movementList = movementRepository.ReturnMovementsByIDList(idList);
        //assert
        assertNotNull(movementList);
    }
    @Test
    public void ReturnMovementListByIDListTestException(){
        //arrange
        List<Integer> idList = List.of(99,100,101);
        //act
        List<Movement> movementList = movementRepository.ReturnMovementsByIDList(idList);
        //assert
        assertTrue(movementList.isEmpty());
    }
    @Test
    public void SaveUpdatedMovementListTest(){
        List<Integer> idList = List.of(1,2,3);
        List<Movement> movementList = movementRepository.ReturnMovementsByIDList(idList);
        for (Movement mov : movementList){
            mov.setMovementDays(5);
            mov.setMovementComment("Updated");
        }
        movementRepository.SaveMovementList(movementList);
        List<Movement> updatedList = movementRepository.ReturnMovementsByIDList(idList);

        // Assert - Check list size
        assertNotNull(updatedList, "Updated list should not be null");
        assertEquals(3, updatedList.size(), "Should return 3 updated movements");

        // Assert - Check that each movement has been updated
        for (Movement updated : updatedList) {
            assertEquals(5, updated.getMovementDays(), "MovementDays should be updated to 5");
            assertEquals("Updated", updated.getMovementComment(), "MovementComment should be 'Updated'");
        }

    }
}

