package com.DEVLOP.IntegrationTests.APITests;

import com.DEVLOP.Application.DTOS.MovementDto;
import com.DEVLOP.Application.Mappers.IMovementMapper;
import com.DEVLOP.Entities.Equipment;
import com.DEVLOP.Entities.Movement;
import com.DEVLOP.Factories.EquipmentFactory;
import com.DEVLOP.Factories.MovementFactory;
import com.DEVLOP.Repositories.EquipmentRepository;
import com.DEVLOP.Repositories.MovementRepository;
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
public class MovementCommandIntegrationTest {
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource //testcontainer setup
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

    @Autowired
    private MockMvc mockMvc; // Used to test REST endpoints
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Qualifier("IMovementMapperImpl")
    @Autowired
    private IMovementMapper mapper;

    private Equipment equipment;
    private Movement movement;
    private MovementDto movementDto;

    @BeforeEach
    public void setUp() {
        movementRepository.DeleteAllMovements();
        equipment = EquipmentFactory.CreateEquipment();
        equipmentRepository.PersistEquipmentClass(equipment.getEquipmentType().getEquipmentClass());
        equipmentRepository.PersistEquipmentType(equipment.getEquipmentType());
        equipment = equipmentRepository.PersistEquipment(equipment);
        movement = MovementFactory.CreateMovement(equipment);
        movementRepository.PersistMovement(movement);
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

        Movement updatedEquipment = movementRepository.FindMovementById(id).orElse(null);
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
        // Arrange - create 2 more movements for group update
        Movement movement2 = MovementFactory.CreateMovement(equipment);
        Movement movement3 = MovementFactory.CreateMovement(equipment);

        movement2 = movementRepository.PersistMovement(movement2);
        movement3 = movementRepository.PersistMovement(movement3);

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
}
