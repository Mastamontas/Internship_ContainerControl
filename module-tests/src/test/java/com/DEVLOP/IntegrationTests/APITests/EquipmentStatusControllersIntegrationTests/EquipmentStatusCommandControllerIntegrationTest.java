package com.DEVLOP.IntegrationTests.APITests.EquipmentStatusControllersIntegrationTests;


import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
import com.DEVLOP.Application.DTOS.EquipmentStatusDto;
import com.DEVLOP.Entities.EquipmentLeasing;
import com.DEVLOP.Entities.EquipmentStatus;
import com.DEVLOP.Factories.EquipmentLeasingFactory;
import com.DEVLOP.Factories.EquipmentStatusFactory;
import com.DEVLOP.Repositories.EquipmentStatusRepo;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentStatusCommandControllerIntegrationTest {

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
    private EquipmentStatusRepo equipmentStatusRepo;

    @Test
    public void CreateNewEquipmentStatus_Test() throws Exception {
        //arrange
        EquipmentStatusDto equipmentStatusDto = new EquipmentStatusDto();
        equipmentStatusDto.setEquipmentStatusCode("AAA");
        equipmentStatusDto.setEquipmentStatusName("Test");
        equipmentStatusDto.setEquipmentStatusLevel1("Status-1");


        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentStatusDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/equipmentStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.equipmentStatusCode").value("AAA"))
                .andExpect(jsonPath("$.equipmentStatusName").value("Test"))
                .andExpect(jsonPath("$.equipmentStatusLevel1").value("Status-1"));

        EquipmentStatus equipmentStatus = equipmentStatusRepo.ReturnEquipmentStatusByCode("AAA").orElseThrow();

        assertEquals("AAA", equipmentStatus.getEquipmentStatusCode());
    }

    @Test
    public void UpdateEquipmentStatus_Test() throws Exception{
        //arrange
        EquipmentStatus equipmentStatus = EquipmentStatusFactory.CreateEquipmentStatus();
        equipmentStatusRepo.PersistEquipmentStatus(equipmentStatus);

        EquipmentStatus testEquipmentPersisted = equipmentStatusRepo.ReturnEquipmentStatusByID(1).orElseThrow();
        String oldCode = testEquipmentPersisted.getEquipmentStatusCode();
        String oldName = testEquipmentPersisted.getEquipmentStatusName();

        String newCode = "BBB";
        String newName = "Updated-Name";


        EquipmentStatusDto equipmentStatusDto = new EquipmentStatusDto();
        equipmentStatusDto.setId(testEquipmentPersisted.getId());
        equipmentStatusDto.setEquipmentStatusCode("BBB");
        equipmentStatusDto.setEquipmentStatusName("Updated-Name");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentStatusDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipmentLeasing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testEquipmentPersisted.getId()))
                .andExpect(jsonPath("$.equipmentStatusCode").value(newCode))
                .andExpect(jsonPath("$.equipmentStatusName").value(newName))

                .andExpect(jsonPath("$.equipmentStatusCode").value(org.hamcrest.Matchers.not(oldCode)))
                .andExpect(jsonPath("$.equipmentStatusName").value(org.hamcrest.Matchers.not(oldName)));


        EquipmentStatus updatedEntity = equipmentStatusRepo.ReturnEquipmentStatusByID(testEquipmentPersisted.getId()).orElseThrow();
        assertEquals(newCode, updatedEntity.getEquipmentStatusCode());
        assertEquals(newName, updatedEntity.getEquipmentStatusName());


    }
}
