package com.DEVLOP.IntegrationTests.APITests.EquipmentLeasingControllersIntegrationTests;

import com.DEVLOP.Application.DTOS.EquipmentClassDto;
import com.DEVLOP.Application.DTOS.EquipmentConditionDto;
import com.DEVLOP.Application.DTOS.EquipmentLeasingDto;
import com.DEVLOP.Entities.EquipmentCondition;
import com.DEVLOP.Entities.EquipmentLeasing;
import com.DEVLOP.Factories.EquipmentConditionFactory;
import com.DEVLOP.Factories.EquipmentLeasingFactory;
import com.DEVLOP.Repositories.EquipmentLeasingRepo;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //important for test containers
@AutoConfigureMockMvc
@Testcontainers
public class EquipmentLeasingCommandControllerIntegrationTest {

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
    private EquipmentLeasingRepo equipmentLeasingRepo;

    @Test
    public void CreateNewEquipmentLeasing_Test() throws Exception {
        //arrange
        EquipmentLeasingDto equipmentLeasingDto = new EquipmentLeasingDto();
        equipmentLeasingDto.setLeasingContractCode("AAA");
        equipmentLeasingDto.setLeasingContractName("Test");


        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentLeasingDto);

        //act
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/v1/equipmentLeasing")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(request().asyncStarted())
                .andReturn();

        //assert
        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.leasingContractCode").value("AAA"))
                .andExpect(jsonPath("$.leasingContractName").value("Test"));

        EquipmentLeasing equipmentLeasing = equipmentLeasingRepo.ReturnEquipmentLeasingByCode("AAA").orElseThrow();
        //assertEquals(1, equipmentLeasing.getId());
        assertEquals("AAA", equipmentLeasing.getLeasingContractCode());
    }

    @Test
    public void UpdateEquipmentLeasing_Test() throws Exception{
        //arrange
        EquipmentLeasing equipmentLeasing = EquipmentLeasingFactory.CreateEquipmentLeasing();
        equipmentLeasingRepo.PersistEquipmentLeasing(equipmentLeasing);

        EquipmentLeasing testEquipmentPersisted = equipmentLeasingRepo.ReturnEquipmentLeasingByID(1).orElseThrow();
        String oldCode = testEquipmentPersisted.getLeasingContractCode();
        String oldName = testEquipmentPersisted.getLeasingContractName();

        String newCode = "BBB";
        String newName = "Updated-Name";


        EquipmentLeasingDto equipmentLeasingDto = new EquipmentLeasingDto();
        equipmentLeasingDto.setId(testEquipmentPersisted.getId());
        equipmentLeasingDto.setLeasingContractCode("BBB");
        equipmentLeasingDto.setLeasingContractName("Updated-Name");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(equipmentLeasingDto);

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
                .andExpect(jsonPath("$.leasingContractCode").value(newCode))
                .andExpect(jsonPath("$.leasingContractName").value(newName))

                .andExpect(jsonPath("$.leasingContractCode").value(org.hamcrest.Matchers.not(oldCode)))
                .andExpect(jsonPath("$.leasingContractName").value(org.hamcrest.Matchers.not(oldName)));


        EquipmentLeasing updatedEntity = equipmentLeasingRepo.ReturnEquipmentLeasingByID(testEquipmentPersisted.getId()).orElseThrow();
        assertEquals(newCode, updatedEntity.getLeasingContractCode());
        assertEquals(newName, updatedEntity.getLeasingContractName());


    }
}
