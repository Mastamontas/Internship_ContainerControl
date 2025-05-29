package com.DEVLOP.IntegrationTests.APITests.MovementTypeControllersIntegrationTests;


import com.DEVLOP.Application.DTOS.MovementTypeDto;
import com.DEVLOP.Entities.EquipmentService;
import com.DEVLOP.Entities.EquipmentType;
import com.DEVLOP.Entities.MovementType;
import com.DEVLOP.Factories.MovementTypeFactory;
import com.DEVLOP.Repositories.MovementTypeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class MovementTypeCommandControllerIntegrationTest {
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest");

    @DynamicPropertySource //testcontainer setup
    static void mySqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovementTypeRepo movementTypeRepo;

    @Test
    public void CreateNewMovementType_Test() throws Exception{
        MovementTypeDto movementTypeDto = new MovementTypeDto();
        movementTypeDto.setMovementTypeCode("AAA");
        movementTypeDto.setMovementTypeName("Name");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson =  objectMapper.writeValueAsString(movementTypeDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/movementType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.movementTypeCode").value("AAA"))
                .andExpect(jsonPath("$.movementTypeName").value("Name"));

        MovementType movementType = movementTypeRepo.ReturnMovementTypeByCode("AAA").orElseThrow();
        assertEquals(1, movementType.getId());
        assertEquals("AAA", movementType.getMovementTypeCode());
    }

    @Test
    public void UpdateMovementType_Test() throws Exception{
        MovementType persistedMovementType = MovementTypeFactory.CreateMovementType();
        movementTypeRepo.PersistMovementType(persistedMovementType);

        MovementType movementTypeToUpdate = movementTypeRepo.FindMovementTypeByID(1).orElseThrow();
        String oldCode = movementTypeToUpdate.getMovementTypeCode();
        String oldName = movementTypeToUpdate.getMovementTypeName();

        String newCode = "new code";
        String newName = "new name";

        MovementTypeDto movementTypeDto = new MovementTypeDto();
        movementTypeDto.setId(movementTypeToUpdate.getId());
        movementTypeDto.setMovementTypeCode(newCode);
        movementTypeDto.setMovementTypeName(newName);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(movementTypeDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipmentType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(movementTypeToUpdate.getId()))
                .andExpect(jsonPath("$.movementTypeCode").value(newCode))
                .andExpect(jsonPath("$.movementTypeName").value(newName))
                .andExpect(jsonPath("$.movementTypeCode").value(org.hamcrest.Matchers.not(oldCode)))
                .andExpect(jsonPath("$.movementTypeName").value(org.hamcrest.Matchers.not(oldName)));

        MovementType movementTypeUpdated = movementTypeRepo.FindMovementTypeByID(movementTypeToUpdate.getId()).orElseThrow();
        assertEquals(newCode, movementTypeUpdated.getMovementTypeCode());
        assertEquals(newName, movementTypeUpdated.getMovementTypeName());
    }
}
