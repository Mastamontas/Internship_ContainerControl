package com.DEVLOP.IntegrationTests.PersistenceTests;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.Equipment;
import com.DEVLOP.Repositories.Movement;
import com.DEVLOP.Specifications.MovementSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/*
todo feature add group movement
 */
@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovementIntegrationTest {


    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @Autowired
    private Equipment equipment;

    @Autowired
    private Movement movement;
    @Autowired
    private MovementSpecification movementSpecification;
    @Autowired
    @Qualifier("IMovementMapperImpl")
    private IMovementMapper mapper;

    @DynamicPropertySource
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }
    private com.DEVLOP.Entities.Equipment testEquipment;
    private com.DEVLOP.Entities.Equipment testEquipment2;
    private com.DEVLOP.Entities.Equipment testEquipment3;
    private com.DEVLOP.Entities.Equipment testEquipment4;

    private List<com.DEVLOP.Entities.Equipment> mockEquipmentList;
    @BeforeEach
    public void SetUp(){
        this.mapper = mapper;
        testEquipment = EquipmentFactory.CreateEquipment();

        //mudar nome metodo para persistEquipment
        equipment.PersistEquipmentClass(testEquipment.getEquipmentType().getEquipmentClass());
        equipment.PersistEquipmentType(testEquipment.getEquipmentType());
        equipment.PersistEquipment(testEquipment);

        testEquipment2 = EquipmentFactory.CreateEquipment();
        equipment.PersistEquipmentClass(testEquipment2.getEquipmentType().getEquipmentClass());
        equipment.PersistEquipmentType(testEquipment2.getEquipmentType());
        equipment.PersistEquipment(testEquipment2);

        testEquipment3 = EquipmentFactory.CreateEquipment();
        equipment.PersistEquipmentClass(testEquipment3.getEquipmentType().getEquipmentClass());
        equipment.PersistEquipmentType(testEquipment3.getEquipmentType());
        equipment.PersistEquipment(testEquipment3);

        testEquipment4 = EquipmentFactory.CreateEquipment();
        equipment.PersistEquipmentClass(testEquipment4.getEquipmentType().getEquipmentClass());
        equipment.PersistEquipmentType(testEquipment4.getEquipmentType());
        equipment.PersistEquipment(testEquipment4);




        IntStream.rangeClosed(1,4).forEach(i->{
            com.DEVLOP.Entities.Movement testMovement = MovementFactory.CreateMovement(testEquipment);
            movement.PersistMovement(testMovement);
        });
    }
    @Test
    public void ReturnsListByDateOrder(){
        //setup ir buscar equipamento com id 3
        com.DEVLOP.Entities.Equipment eq = equipment.FindAll().stream().findFirst().orElseThrow(()-> new EquipmentNotFoundException("no equip in test"));
        //act
        List<com.DEVLOP.Entities.Movement> orderedMovementList = movement.GetMovementsOfEquipment(eq);
        //assertions here
        for (com.DEVLOP.Entities.Movement m : orderedMovementList){
            System.out.println(m.getDate());
            System.out.println(m.getEquipment().getId());
            System.out.println(m.getEquipment().getPrefix());
        }
    }
    @Test
    public void FindMovementByIDSuccessTest(){
        //arrange
        com.DEVLOP.Entities.Movement mov1 = movement.FindMovementById(1).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        com.DEVLOP.Entities.Movement mov2 = movement.FindMovementById(2).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        com.DEVLOP.Entities.Movement mov3 = movement.FindMovementById(3).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        com.DEVLOP.Entities.Movement mov4 = movement.FindMovementById(4).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));

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
            movement.FindMovementById(5)
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
        Specification<com.DEVLOP.Entities.Movement> spec =  movementSpecification.BuildSpecification(filter1);
        List<com.DEVLOP.Entities.Movement> moveList1 = movement.ReturnFilteredMovementList(spec);
        assertNotNull(moveList1);

    }
    @Test
    public void ReturnMovementListByIDListTest(){
        //arrange
        List<Integer> idList = List.of(1,2,3);
        //act
        List<com.DEVLOP.Entities.Movement> movementList = movement.ReturnMovementsByIDList(idList);
        //assert
        assertNotNull(movementList);
    }
    @Test
    public void ReturnMovementListByIDListTestException(){
        //arrange
        List<Integer> idList = List.of(99,100,101);
        //act
        List<com.DEVLOP.Entities.Movement> movementList = movement.ReturnMovementsByIDList(idList);
        //assert
        assertTrue(movementList.isEmpty());
    }
    @Test
    public void SaveUpdatedMovementListTest(){
        List<Integer> idList = List.of(1,2,3);
        List<com.DEVLOP.Entities.Movement> movementList = movement.ReturnMovementsByIDList(idList);
        for (com.DEVLOP.Entities.Movement mov : movementList){
            mov.setMovementDays(5);
            mov.setMovementComment("Updated");
        }
        movement.SaveMovementList(movementList);
        List<com.DEVLOP.Entities.Movement> updatedList = movement.ReturnMovementsByIDList(idList);

        // Assert - Check list size
        assertNotNull(updatedList, "Updated list should not be null");
        assertEquals(3, updatedList.size(), "Should return 3 updated movements");

        // Assert - Check that each movement has been updated
        for (com.DEVLOP.Entities.Movement updated : updatedList) {
            assertEquals(5, updated.getMovementDays(), "MovementDays should be updated to 5");
            assertEquals("Updated", updated.getMovementComment(), "MovementComment should be 'Updated'");
        }

    }
    @Test
    public void AddGroupMovement(){
        //arrange
        List<Integer> idList = List.of(2,3,4);
        List<com.DEVLOP.Entities.Equipment> equipmentList = equipment.GetEquipmentListFromID(idList);
        MovementDto movementDtoToAdd = new MovementDto();

        movementDtoToAdd.setDate(LocalDateTime.now());
        movementDtoToAdd.setAccessUserID(1);
        movementDtoToAdd.setBusinessUnitID(1);
        movementDtoToAdd.setTransportResponsibility("Julio");
        movementDtoToAdd.setMovementOfHire("helder");
        movementDtoToAdd.setMovementRestitutionCode(1);
        movementDtoToAdd.setMovementVoyageID(1);
        movementDtoToAdd.setEquipmentOwnerID(1);
        movementDtoToAdd.setMovementDays(2);
        movementDtoToAdd.setMovementLast(false);
        movementDtoToAdd.setShipmentUCN("barcos");
        movementDtoToAdd.setMovementTransport("outro barco");
        movementDtoToAdd.setTransportMeansComment("comentario transport means");
        movementDtoToAdd.setMovementStatus("movement status");
        //movement type
        movementDtoToAdd.setMovementTypeCode("movement type code");
        movementDtoToAdd.setMovementTypeName("movementTypeName");
        //equipment status
        movementDtoToAdd.setEquipmentStatusCode("equipment status code");
        movementDtoToAdd.setEquipmentStatusName("equipment status name");
        movementDtoToAdd.setEquipmentStatusLevel1("equipment status level 1");
        movementDtoToAdd.setEquipmentStatusLevel2("equipment status level 2");
        //equipment condition
        movementDtoToAdd.setPhysicalConditionCode("physical condition code");
        movementDtoToAdd.setPhysicalConditionName("physical condition name");
        movementDtoToAdd.setPhysicalConditionType("physical condition type");
        //equipment service
        movementDtoToAdd.setEquipmentServiceCode("Equipment service code");
        movementDtoToAdd.setEquipmentServiceName("equipment service name");
        //equipment leasing
        movementDtoToAdd.setEquipmentLeasingCode("equipment leasing code");
        movementDtoToAdd.setEquipmentLeasingName("equipment leasing name");


        // Act
        // Map and associate to each equipment
        List<com.DEVLOP.Entities.Movement> newMovements = equipmentList.stream().map(eq -> {
            com.DEVLOP.Entities.Movement movement = mapper.MapToMovementEntity(movementDtoToAdd);
            movement.setEquipment(eq);
            movement.setEquipmentType(eq.getEquipmentType());
            return movement;
        }).toList();

        // Act: Save through repository
        /*
        o problema é aqui, ele tenta guardar as mesmas entidades que estão nested.
        idealmente, as entidades que estao integradas nos movements podem ser criadas e guardadas no sistema.
        depois são so chamadas, como quando se cria os movimentos na factory
         */
        movement.SaveMovementList(newMovements);

        // Assert: each of the movements saved should have the equipment selected associated with it
        List<com.DEVLOP.Entities.Movement> persistedMovements = movement.FindAllMovements();

        for (com.DEVLOP.Entities.Equipment eq : equipmentList) {
            boolean found = persistedMovements.stream()
                    .anyMatch(mov ->
                            mov.getEquipment() != null &&
                                    Objects.equals(mov.getEquipment().getId(), eq.getId()) &&
                                    mov.getMovementType().getMovementTypeCode().equals("movement type code")
                    );

            assertTrue(found, "Movement for equipment " + eq.getPrefix() + " should have been saved");
        }

    }
}

