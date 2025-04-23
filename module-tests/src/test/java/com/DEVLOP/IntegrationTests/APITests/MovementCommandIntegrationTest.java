package com.DEVLOP.IntegrationTests.APITests;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Entities.*;
import com.DEVLOP.Factories.*;
import com.DEVLOP.Repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class MovementRepoCommandIntegrationTest {
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource //testcontainer setup
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }
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
    private MockMvc mockMvc; // Used to test REST endpoints
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private MovementRepo movementRepo;
    @Autowired
    private ObjectMapper objectMapper;
    @Qualifier("IMovementMapperImpl")
    @Autowired
    private IMovementMapper mapper;

    private Equipment equipment;
    private Movement movement;
    private Movement movement2;
    private Movement movement3;
    private MovementDto movementDto;

    @BeforeEach
    public void setUp() {
        movementRepo.DeleteAllMovements();
        equipment = EquipmentFactory.CreateEquipment();
        equipmentRepo.PersistEquipmentClass(equipment.getEquipmentType().getEquipmentClass());
        equipmentRepo.PersistEquipmentType(equipment.getEquipmentType());
        equipment = equipmentRepo.PersistEquipment(equipment);
        /*
        falta adicionar aqui todos os elementos necessários dos movimentos
         */
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


        movement = MovementFactory.CreateMovement(equipment);
        movement2 = MovementFactory.CreateMovement(equipment);
        movement3 = MovementFactory.CreateMovement(equipment);

        movement.setMovementType(moveType);
        movement.setEquipmentStatus(equipStatus);
        movement.setEquipmentCondition(equipCondition);
        movement.setEquipmentService(equipService);
        movement.setEquipmentLeasing(equipLeasing);
        movement.setTransportMeans(transMeans);
        movement.setMovementType(moveType);
        movement.setEquipmentStatus(equipStatus);
        movement.setEquipmentCondition(equipCondition);
        movement.setEquipmentService(equipService);
        movement.setEquipmentLeasing(equipLeasing);
        movement.setTransportMeans(transMeans);

        movement2.setMovementType(moveType);
        movement2.setEquipmentStatus(equipStatus);
        movement2.setEquipmentCondition(equipCondition);
        movement2.setEquipmentService(equipService);
        movement2.setEquipmentLeasing(equipLeasing);
        movement2.setTransportMeans(transMeans);
        movement2.setMovementType(moveType);
        movement2.setEquipmentStatus(equipStatus);
        movement2.setEquipmentCondition(equipCondition);
        movement2.setEquipmentService(equipService);
        movement2.setEquipmentLeasing(equipLeasing);
        movement2.setTransportMeans(transMeans);

        movement3.setMovementType(moveType);
        movement3.setEquipmentStatus(equipStatus);
        movement3.setEquipmentCondition(equipCondition);
        movement3.setEquipmentService(equipService);
        movement3.setEquipmentLeasing(equipLeasing);
        movement3.setTransportMeans(transMeans);
        movement3.setMovementType(moveType);
        movement3.setEquipmentStatus(equipStatus);
        movement3.setEquipmentCondition(equipCondition);
        movement3.setEquipmentService(equipService);
        movement3.setEquipmentLeasing(equipLeasing);
        movement3.setTransportMeans(transMeans);
        // Arrange - create 2 more movements for group update


        movement2 = movementRepo.PersistMovement(movement2);
        movement3 = movementRepo.PersistMovement(movement3);

        movementRepo.PersistMovement(movement);
        movementDto = mapper.MapToMovementDto(movement);
    }

    @Test
    public void testUpdateMovement_Success() throws Exception {
        //ver se async comeca
        int id = movement.getId();
        movementDto.setComments("Updated comments");
        // Act & Assert


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/movements/{id}/update", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movementDto)))
                .andExpect(request().asyncStarted()) // Verify async started
                .andReturn(); // Capture the MvcResult
        mockMvc.perform(asyncDispatch(mvcResult)) // Dispatch asynchronously
                .andExpect(status().isOk())
                .andExpect(content().string("Movement was updated"));

        com.DEVLOP.Entities.Movement updatedEquipment = movementRepo.FindMovementById(id).orElse(null);
        assert updatedEquipment != null;
        assertEquals("Updated comments", movementDto.getComments());
    }

    @Test
    public void testUpdateMovement_Failure() throws Exception {
        // Act & Assert
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/movements/{id}/update", 500)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movementDto)))
                .andExpect(request().asyncStarted()) // Verify async started
                .andReturn(); // Capture the MvcResult

        mockMvc.perform(asyncDispatch(mvcResult)) // Dispatch asynchronously
                .andExpect(status().isNotFound())
                .andExpect(content().string("movement was not updated"));
    }

    @Test
    public void ChangeGroupMovementTest() throws Exception{


        // List of IDs to update
        String queryParam = String.format("movementIDs=%d&movementIDs=%d&movementIDs=%d",
                movement.getId(), movement2.getId(), movement3.getId());

        // Build update payload
        //create new movement dto
        MovementDto updatedDto = new MovementDto();
        updatedDto.setTransportResponsibility("Group update test");
        updatedDto.setComments("Updated in bulk");

        // Act: perform async call
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .patch("/v1/movements/updateGroup?" + queryParam)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(request().asyncStarted())
                .andReturn();

        // Assert: dispatch async and validate response
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3)) // You updated 3 movements
                .andExpect(jsonPath("$[0].transportResponsibility").value("Group update test"))
                .andExpect(jsonPath("$[0].comments").value("Updated in bulk"));
    }
    /*
    add movement to group of equipments
     */
}
