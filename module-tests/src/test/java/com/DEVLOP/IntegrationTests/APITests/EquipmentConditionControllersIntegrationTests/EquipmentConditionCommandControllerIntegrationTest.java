package com.DEVLOP.IntegrationTests.APITests.EquipmentConditionControllersIntegrationTests;

import com.DEVLOP.Application.Commands.EquipmentConditionCommand;
import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import com.DEVLOP.Entities.EquipmentCondition;
import com.DEVLOP.Factories.EquipmentConditionFactory;
import com.DEVLOP.Repositories.EquipmentConditionRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentConditionCommandControllerIntegrationTest {
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
    private EquipmentConditionRepo equipmentConditionRepo;

    @Test
    public void CreateNewEquipmentConditionTest() throws Exception {
        EquipmentConditionDto equipmentConditionDto = new EquipmentConditionDto();
        equipmentConditionDto.setPhysicalConditionCode("AAA");
        equipmentConditionDto.setPhysicalConditionName("Name");
        equipmentConditionDto.setPhysicalConditionType("type");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentConditionDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/equipmentCondition/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.physicalConditionCode").value("AAA"))
                .andExpect(jsonPath("$.physicalConditionName").value("Name"));

        EquipmentCondition equipmentCondition = equipmentConditionRepo.FindEquipmentConditionByCode("AAA").orElseThrow();
        assertEquals(1, equipmentCondition.getId());
        assertEquals("AAA", equipmentCondition.getPhysicalConditionCode());
    }

    @Test
    public void UpdateEquipmentCondition_Test() throws Exception{
        //arrange
        EquipmentCondition equipmentCondition = EquipmentConditionFactory.CreateEquipmentCondition();
        equipmentConditionRepo.PersistEquipmentCondition(equipmentCondition);

        EquipmentCondition testEquipmentPersisted = equipmentConditionRepo.FindEquipmentConditionByID(1).orElseThrow();
        String oldCode = testEquipmentPersisted.getPhysicalConditionCode();
        String oldName = testEquipmentPersisted.getPhysicalConditionName();
        String oldType = testEquipmentPersisted.getPhysicalConditionType();

        String newCode = "BBB";
        String newName = "Updated-Name";
        String newType = "Updated-Type";

        EquipmentConditionDto equipmentConditionDto = new EquipmentConditionDto();
        equipmentConditionDto.setId(testEquipmentPersisted.getId());
        equipmentConditionDto.setPhysicalConditionCode("BBB");
        equipmentConditionDto.setPhysicalConditionName("Updated-Name");
        equipmentConditionDto.setPhysicalConditionType("Updated-Type");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentConditionDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/v1/equipmentCondition/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testEquipmentPersisted.getId()))
                .andExpect(jsonPath("$.physicalConditionCode").value(newCode))
                .andExpect(jsonPath("$.physicalConditionName").value(newName))
                .andExpect(jsonPath("$.physicalConditionType").value(newType))
                .andExpect(jsonPath("$.physicalConditionCode").value(org.hamcrest.Matchers.not(oldCode)))
                .andExpect(jsonPath("$.physicalConditionName").value(org.hamcrest.Matchers.not(oldName)))
                .andExpect(jsonPath("$.physicalConditionType").value(org.hamcrest.Matchers.not(oldType)));

        EquipmentCondition updatedEntity = equipmentConditionRepo.FindEquipmentConditionByID(testEquipmentPersisted.getId()).orElseThrow();
        assertEquals(newCode, updatedEntity.getPhysicalConditionCode());
        assertEquals(newName, updatedEntity.getPhysicalConditionName());
        assertEquals(newType, updatedEntity.getPhysicalConditionType());

    }

}
