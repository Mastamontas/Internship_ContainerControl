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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            Movement testMovement = MovementFactory.CreateMovementEntity(testEquipment);
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
    @Test
    public void FindMovementByIDSuccessFail(){
        // Act & Assert - Check if the expected exception is thrown
        assertThrows(MovementNotFoundException.class, () -> {
            movementRepository.FindMovementById(5)
                    .orElseThrow(() -> new MovementNotFoundException("movement does not exist"));
        });
    }
    /*
    test save all
    test return filtered movements
    remove filters and it returns the entire unfiltered list
     */
    @Test
    public void ReturnAllMovementsFiltered(){
        //set up the spec
        Map<String, Object> filter1 = new HashMap<>();
        /*Map<String, Object> filter2 = new HashMap<>();*/
        filter1.put("equipment.prefix",testEquipment.getPrefix());
        filter1.put("equipmentStatus.equipmentStatusCode", "IN_PROGRESS");
        //filter2.put("equipment.prefix",testEquipment2.getPrefix());

        Specification<Movement> spec =  movementSpecification.BuildSpecification(filter1);
        /*Specification<Movement> spec2 =  movementSpecification.BuildSpecification(filter2);*/

        List<Movement> moveList1 = movementRepository.ReturnFilteredMovementList(spec);
        /*List<Movement> moveList2 = movementRepository.ReturnFilteredMovementList(spec2);*/

        for (Movement mov : moveList1){
            System.out.println(mov.getId());
            System.out.println(mov.getDate());
        }
        /*for (Movement mov : moveList2){
            System.out.println(mov.getId());
            System.out.println(mov.getDate());
        }*/
        assertNotNull(moveList1);
    }


    /*
    todo
    test more filters
    test no filters
    test error case
     */



}

