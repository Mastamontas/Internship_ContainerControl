package com.DEVLOP.IntegrationTests.PersistenceTests;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.CustomExceptions.EquipmentNotFoundException;
import com.DEVLOP.CustomExceptions.Movement.MovementNotFoundException;
import com.DEVLOP.Entities.*;
import com.DEVLOP.Factories.*;
import com.DEVLOP.Repositories.*;
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


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

//todo em conjunto alguns testes falham por cada set up estar a adicionar elementos às tabelas
@AutoConfigureMockMvc
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovementRepoIntegrationTest {


    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @Autowired
    private EquipmentServiceRepo equipmentServiceRepo;
    @Autowired
    private EquipmentLeasingRepo equipmentLeasingRepo;
    @Autowired
    private EquipmentConditionRepo equipmentConditionRepo;
    @Autowired
    private EquipmentStatusRepo equipmentStatusRepo;
    @Autowired
    private MovementTypeRepo movementTypeRepo;
    @Autowired
    private TransportMeansRepo transportMeansRepo;

    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private MovementRepo movementRepo;


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
    private Equipment testEquipment;
    private Equipment testEquipment2;
    private Equipment testEquipment3;
    private Equipment testEquipment4;

    private List<com.DEVLOP.Entities.Equipment> mockEquipmentList;
    @BeforeEach
    public void SetUp(){
        /*
        delete all equipments and movements
         */
        testEquipment = EquipmentFactory.CreateEquipment();

        //mudar nome metodo para persistEquipment
        //todo: os equipment types e classes tem de ser criados à parte do equipment repo (equipmentClassRepo; EquipmentTypeRepo);
        equipmentRepo.PersistEquipmentClass(testEquipment.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(testEquipment.getEquipmentType());
        equipmentRepo.PersistEquipment(testEquipment);

        testEquipment2 = EquipmentFactory.CreateEquipment();
        equipmentRepo.PersistEquipmentClass(testEquipment2.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(testEquipment2.getEquipmentType());
        equipmentRepo.PersistEquipment(testEquipment2);

        testEquipment3 = EquipmentFactory.CreateEquipment();
        equipmentRepo.PersistEquipmentClass(testEquipment3.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(testEquipment3.getEquipmentType());
        equipmentRepo.PersistEquipment(testEquipment3);

        testEquipment4 = EquipmentFactory.CreateEquipment();
        equipmentRepo.PersistEquipmentClass(testEquipment4.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(testEquipment4.getEquipmentType());
        equipmentRepo.PersistEquipment(testEquipment4);

        //-----Nested entities of movement
        MovementType moveType = MovementTypeFactory.CreateMovementType();
        movementTypeRepo.PersistMovementType(moveType);

        EquipmentCondition equipCondition = EquipmentConditionFactory.CreateEquipmentCondition();
        equipmentConditionRepo.PersistEquipmentCondition(equipCondition);

        EquipmentStatus equipStatus = EquipmentStatusFactory.CreateEquipmentStatus();
        equipmentStatusRepo.PersistEquipmentStatus(equipStatus);

        EquipmentService equipService = EquipmentServiceFactory.CreateEquipmentService();
        equipmentServiceRepo.PersistEquipmentService(equipService);

        EquipmentLeasing equipLeasing = EquipmentLeasingFactory.CreateEquipmentLeasing();
        equipmentLeasingRepo.PersistEquipmentLeasing(equipLeasing);

        TransportMeans transMeans = new TransportMeans();
        transportMeansRepo.PersistTransportMeans(transMeans);


        IntStream.rangeClosed(1,4).forEach(i->{
            Movement testMovement = MovementFactory.CreateMovement(testEquipment);
            testMovement.setMovementType(moveType);
            testMovement.setEquipmentStatus(equipStatus);
            testMovement.setEquipmentCondition(equipCondition);
            testMovement.setEquipmentService(equipService);
            testMovement.setEquipmentLeasing(equipLeasing);
            testMovement.setTransportMeans(transMeans);
            movementRepo.PersistMovement(testMovement);
        });
    }
    @Test
    public void ReturnsListByDateOrder(){
        //setup ir buscar equipamento com id 3
        Equipment eq = equipmentRepo.FindAll().stream().findFirst().orElseThrow(()-> new EquipmentNotFoundException("no equip in test"));
        //act
        List<Movement> orderedMovementList = movementRepo.GetMovementsOfEquipment(eq);
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
        Movement mov1 = movementRepo.FindMovementById(1).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        Movement mov2 = movementRepo.FindMovementById(2).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        Movement mov3 = movementRepo.FindMovementById(3).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));
        Movement mov4 = movementRepo.FindMovementById(4).orElseThrow(()-> new MovementNotFoundException("movement does not exist"));

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
            movementRepo.FindMovementById(5)
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
        List<com.DEVLOP.Entities.Movement> moveList1 = movementRepo.ReturnFilteredMovementList(spec);
        assertNotNull(moveList1);

    }
    @Test
    public void ReturnMovementListByIDListTest(){
        //arrange
        List<Integer> idList = List.of(1,2,3);
        //act
        List<Movement> movementList = movementRepo.ReturnMovementsByIDList(idList);
        //assert
        assertNotNull(movementList);
    }
    @Test
    public void ReturnMovementListByIDListTestException(){
        //arrange
        List<Integer> idList = List.of(99,100,101);
        //act
        List<Movement> movementList = movementRepo.ReturnMovementsByIDList(idList);
        //assert
        assertTrue(movementList.isEmpty());
    }
    @Test
    public void SaveUpdatedMovementListTest(){
        List<Integer> idList = List.of(1,2,3);
        List<Movement> movementList = movementRepo.ReturnMovementsByIDList(idList);
        for (Movement mov : movementList){
            mov.setMovementDays(5);
            mov.setMovementComment("Updated");
        }
        movementRepo.SaveMovementList(movementList);
        List<Movement> updatedList = movementRepo.ReturnMovementsByIDList(idList);

        // Assert - Check list size
        assertNotNull(updatedList, "Updated list should not be null");
        assertEquals(3, updatedList.size(), "Should return 3 updated movements");

        // Assert - Check that each movement has been updated
        for (Movement updated : updatedList) {
            assertEquals(5, updated.getMovementDays(), "MovementDays should be updated to 5");
            assertEquals("Updated", updated.getMovementComment(), "MovementComment should be 'Updated'");
        }

    }
    @Test
    public void AddGroupMovement(){
        //arrange
        List<Integer> idList = List.of(2,3,4);
        List<Equipment> equipmentList = equipmentRepo.GetEquipmentListFromID(idList);
        MovementDto movementDtoToAdd = new MovementDto();
        movementDtoToAdd.setDate(LocalDate.now());
        movementDtoToAdd.setAccessUserId(1);
        movementDtoToAdd.setBusinessUnitId(1);
        movementDtoToAdd.setTransportResponsibility("Julio");
        movementDtoToAdd.setMovementOfHire("helder");
        movementDtoToAdd.setMovementRestitutionCode(1);
        movementDtoToAdd.setMovementVoyageId(1);
        movementDtoToAdd.setEquipmentOwnerId(1);
        movementDtoToAdd.setMovementDays(2);
        movementDtoToAdd.setMovementLast(false);
        movementDtoToAdd.setShipmentUCN("barcos");
        movementDtoToAdd.setMovementTransport("outro barco");
        movementDtoToAdd.setTransportMeansComment("comentario transport means");
        movementDtoToAdd.setMovementStatus("movement status");
        movementDtoToAdd.setMovementTypeId(1);
        movementDtoToAdd.setEquipmentStatusId(1);
        movementDtoToAdd.setEquipmentConditionId(1);
        EquipmentCondition equipCondition = equipmentConditionRepo.FindEquipmentConditionByID(movementDtoToAdd.getEquipmentConditionId()).orElseThrow();
        movementDtoToAdd.setEquipmentServiceId(1);
        movementDtoToAdd.setEquipmentLeasingId(1);
        TransportMeans transportMeansToAdd = transportMeansRepo.FindTransportMeansByID(1).orElseThrow();



        // Act
        // Map and associate to each equipment
        List<Movement> newMovements = equipmentList.stream().map(eq -> {
            Movement movement = mapper.MapToMovementEntity(movementDtoToAdd);
            movement.setEquipment(eq);
            movement.setEquipmentType(eq.getEquipmentType());
            movement.setMovementType(movementTypeRepo.FindMovementTypeByID(movementDtoToAdd.getMovementTypeId()).orElseThrow());
            movement.setEquipmentService(equipmentServiceRepo.ReturnEquipmentServiceByID(movementDtoToAdd.getEquipmentServiceId()).orElseThrow());
            movement.setEquipmentCondition(equipCondition);
            movement.setEquipmentStatus(equipmentStatusRepo.ReturnEquipmentStatusByID(movementDtoToAdd.getEquipmentStatusId()).orElseThrow());
            movement.setEquipmentLeasing(equipmentLeasingRepo.ReturnEquipmentLeasingByID(movementDtoToAdd.getEquipmentLeasingId()).orElseThrow());
            movement.setTransportMeans(transportMeansToAdd);
            return movement;
        }).toList();

        // Act: Save through repository
        movementRepo.SaveMovementList(newMovements);

        // Assert: each of the movements saved should have the equipment selected associated with it
        List<Movement> persistedMovements = movementRepo.ReturnMovementsWithEquipmentID(idList);
        //need to find movements with the equipment added
        //find movements with equipment ID list (in this case 2,3 4)



        for (Equipment eq : equipmentList) {
            boolean found = persistedMovements.stream()
                    .anyMatch(mov ->
                            mov.getEquipment() != null &&
                                    Objects.equals(mov.getEquipment().getId(), eq.getId()) &&
                                    Objects.equals(mov.getMovementType().getId(), movementDtoToAdd.getMovementTypeId())
                    );

            assertTrue(found, "Movement for equipment " + eq.getPrefix() + " should have been saved");
        }


    }
    @Test
    public void GetAllEntities(){
        // Act: Fetch each entity by its ID (assuming they were given ID 1 during persistence or you stored them)
        MovementType moveType = movementTypeRepo.FindMovementTypeByID(1).orElse(null);
        EquipmentCondition equipCondition = equipmentConditionRepo.FindEquipmentConditionByID(1).orElse(null);
        EquipmentStatus equipStatus = equipmentStatusRepo.ReturnEquipmentStatusByID(1).orElse(null);
        EquipmentService equipService = equipmentServiceRepo.ReturnEquipmentServiceByID(1).orElse(null);
        EquipmentLeasing equipLeasing = equipmentLeasingRepo.ReturnEquipmentLeasingByID(1).orElse(null);

        // Assert: Check they're not null (i.e., successfully persisted and fetched)
        assertNotNull(moveType, "MovementType should be fetched by ID");
        assertNotNull(equipCondition, "EquipmentCondition should be fetched by ID");
        assertNotNull(equipStatus, "EquipmentStatus should be fetched by ID");
        assertNotNull(equipService, "EquipmentService should be fetched by ID");
        assertNotNull(equipLeasing, "EquipmentLeasing should be fetched by ID");
    }
}

